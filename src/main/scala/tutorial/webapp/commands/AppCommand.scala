package tutorial.webapp.commands

import tutorial.webapp.models.Todo

sealed trait AppCommand

//APP LEVEL COMMANDS
case object ClearAll extends AppCommand

case object ClearCompleted extends AppCommand

case class AddTodo(item: String) extends AppCommand

//ITEM LEVEL COMMANDS
case class DeleteTodo(todo: Todo) extends AppCommand

case class MarkAsDone(todo: Todo) extends AppCommand

case class MarkAsUndone(todo: Todo) extends AppCommand
