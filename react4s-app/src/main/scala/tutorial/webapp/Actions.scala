package tutorial.webapp

import diode.Action
import todo_api.model.TodoItem
import tutorial.webapp.state.{Filter, SavedTodo}

object Actions {

  //LIST LEVEL COMMANDS
  case object ClearAll extends Action
  case object ClearCompleted extends Action

  case class AddTodo(item: String) extends Action
  case class TodoAdded(todoItem: TodoItem, unsavedId: String) extends Action
  case class TodoAdditionFailed(unsavedId: String) extends Action

  //FILTER COMMANDS
  case class ApplyFilter(filter: Filter) extends Action

  //ITEM LEVEL COMMANDS
  case class DeleteTodo(todo: SavedTodo) extends Action
  case class MarkAsDone(todo: SavedTodo) extends Action
  case class MarkAsUndone(todo: SavedTodo) extends Action
}
