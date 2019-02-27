package tutorial.webapp

import diode._
import tutorial.webapp.actions._
import tutorial.webapp.models.{All, Filter, Todo}

package object state {
  case class RootModel(todoItems: List[Todo], filter: Filter)

  object AppCircuit extends Circuit[RootModel] {
    override def initialModel: RootModel = RootModel(List.empty, All)

    val todoItemsHandler: ActionHandler[RootModel, List[Todo]] =
      new ActionHandler(zoomTo(_.todoItems)) {
        override protected def handle: PartialFunction[Any, ActionResult[RootModel]] = {
          case ClearAll       => updated(List.empty)
          case ClearCompleted => updated(value.filter(!_.done))
          //ITEM LEVEL
          case AddTodo(item: String) =>
            updated(value :+ Todo(item))
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
        override protected def handle: PartialFunction[Any, ActionResult[RootModel]] = {
          case ApplyFilter(filter) => updated(filter)
        }
    }

    override protected def actionHandler = composeHandlers(todoItemsHandler, filterHandler)
  }
}
