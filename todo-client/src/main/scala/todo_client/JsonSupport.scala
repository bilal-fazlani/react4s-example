package todo_client

import todo_api.dto._
import todo_api.model.TodoItem
import upickle.default.{macroRW, ReadWriter => RW}

object JsonSupport {
  implicit val editRequestRW: RW[EditRequest] = macroRW
  implicit val createRequestRW: RW[CreateRequest] = macroRW
  implicit val deleteRequestRW: RW[DeleteRequest] = macroRW
  implicit val batchDeleteRequestRW: RW[BatchDeleteRequest] = macroRW
  implicit val batchEditRequestRW: RW[BatchEditRequest] = macroRW
  implicit val todoModelRW: RW[TodoItem] = macroRW
}
