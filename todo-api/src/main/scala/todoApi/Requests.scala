package todoApi

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol

case class CreateRequest(text: String, done: Boolean)

object CreateRequest extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val format = jsonFormat2(CreateRequest.apply)
}

case class DeleteRequest(id: String)

object DeleteRequest extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val format = jsonFormat1(DeleteRequest.apply)
}

case class Todo(id: String, text: String, done: Boolean)

object Todo extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val format = jsonFormat3(Todo.apply)
}
