package tutorial.webapp

import tutorial.webapp.models.Todo

package object actions {
  import diode.Action

  trait AppAction extends Action

  case object ClearAll extends AppAction
  case object ClearCompleted extends AppAction
  case class AddTodo(item: String) extends AppAction
  //ITEM LEVEL COMMANDS
  case class DeleteTodo(todo: Todo) extends AppAction
  case class MarkAsDone(todo: Todo) extends AppAction
  case class MarkAsUndone(todo: Todo) extends AppAction
}
