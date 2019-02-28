package todo_api.dto

case class EditRequest(id: String, text: String, done: Boolean)
