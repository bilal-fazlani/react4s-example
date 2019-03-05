package tutorial.webapp

import diode.Effect
import todo_api.dto.CreateRequest
import todo_client.TodoClient
import tutorial.webapp.Actions.{TodoAdded, TodoAdditionFailed}
import tutorial.webapp.state.UnsavedTodo

import scala.concurrent.ExecutionContext.Implicits.global

object Effects {

  val client = new TodoClient()

  def saveTodoItem(unsavedItem: UnsavedTodo) = {
    Effect(
      client
        .create(CreateRequest(unsavedItem.text))
        .map(r => TodoAdded(r, unsavedItem.id))
        .recover {
          case _ => TodoAdditionFailed(unsavedItem.id)
        }
    )
  }
}
