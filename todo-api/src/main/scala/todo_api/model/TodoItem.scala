package todo_api.model

import todo_api.dto.EditRequest

case class TodoItem(id: String, text: String, done: Boolean)

object TodoItem {
  def apply(editReq: EditRequest): TodoItem =
    TodoItem(editReq.id, editReq.text, editReq.done)
}
