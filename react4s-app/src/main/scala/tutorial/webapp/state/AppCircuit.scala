package tutorial.webapp.state

import diode._
import tutorial.webapp.Actions._
import tutorial.webapp.Effects

object AppCircuit extends Circuit[RootModel] {
  override def initialModel: RootModel = RootModel(List.empty, All)

  val todoItemsHandler: ActionHandler[RootModel, List[Todo]] =
    new ActionHandler(zoomTo(_.todoItems)) {
      override protected def handle
        : PartialFunction[Any, ActionResult[RootModel]] = {
        case ClearAll       => updated(List.empty)
        case ClearCompleted => updated(value.filter(!_.done))
        //ITEM LEVEL
        case AddTodo(text: String) =>
          val unsavedTodo = UnsavedTodo(text, done = false)
          updated(value :+ unsavedTodo, Effects.saveTodoItem(unsavedTodo))

        case TodoAdded(item, unsavedId) =>
          updated(
            value.map {
              case x if unsavedId == x.id => SavedTodo(x.text, x.done, item.id)
              case x                      => x
            }
          )

        case DeleteTodo(item) =>
          updated(value.filter(_.id != item.id))
        case MarkAsDone(item) =>
          updated(value.map {
            case `item` => item.copy(done = true)
            case x      => x
          })
        case MarkAsUndone(item) =>
          updated(value.map {
            case `item` => item.copy(done = false)
            case x      => x
          })
      }
    }

  val filterHandler: ActionHandler[RootModel, Filter] =
    new ActionHandler(zoomTo(_.filter)) {
      override protected def handle
        : PartialFunction[Any, ActionResult[RootModel]] = {
        case ApplyFilter(filter) => updated(filter)
      }
    }

  override protected def actionHandler =
    composeHandlers(todoItemsHandler, filterHandler)
}
