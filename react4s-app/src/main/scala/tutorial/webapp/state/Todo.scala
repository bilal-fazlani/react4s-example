package tutorial.webapp.state

import scala.util.Random

sealed trait Todo {
  val text: String
  val done: Boolean
  val id: String
}

case class UnsavedTodo(text: String, done: Boolean) extends Todo {
  val id: String = Random.nextString(10)
}

case class SavedTodo(text: String, done: Boolean, id: String) extends Todo
