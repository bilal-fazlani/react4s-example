package tutorial.webapp

import java.util.UUID

import diode._
import tutorial.webapp.actions._
import tutorial.webapp.models.Todo

package object state {
  case class RootModel(todoItems: List[Todo])

  object AppCircuit extends Circuit[RootModel] {
    override def initialModel: RootModel = RootModel(List.empty)

    val todoItemsHandler: ActionHandler[RootModel, List[Todo]] =
      new ActionHandler(zoomTo(_.todoItems)) {
        override protected def handle = {
          case ClearAll       => updated(List.empty)
          case ClearCompleted => updated(value.filter(!_.done))
          //ITEM LEVEL
          case AddTodo(item: String) =>
            updated(value :+ Todo(UUID.randomUUID().toString, item))
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

    override protected def actionHandler = composeHandlers(todoItemsHandler)
  }
}
