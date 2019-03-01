package tutorial.webapp.models

import java.util.UUID

case class Todo(id: String, text: String, done: Boolean = false)
object Todo{
  def apply(text: String): Todo = new Todo(id = UUID.randomUUID().toString, text, false)
}
