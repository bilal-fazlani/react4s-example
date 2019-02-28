package todo_server

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol
import todo_api.dto._
import todo_api.model.TodoItem

object JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {

  implicit val batchDeleteFormat = jsonFormat1(BatchDeleteRequest)

  implicit val editRequestFormat = jsonFormat3(EditRequest)

  implicit val batchEditFormat = jsonFormat1(BatchEditRequest)

  implicit val createRequestFormat = jsonFormat1(CreateRequest)

  implicit val deleteRequestFormat = jsonFormat1(DeleteRequest)

  implicit val todoFormat = jsonFormat3(TodoItem.apply)
}
