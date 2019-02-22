package todoApi.dto

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol

case class CreateRequest(text: String, done: Boolean)

object CreateRequest extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val format = jsonFormat2(CreateRequest.apply)
}
