package todoApi.dto

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol

case class EditRequest(id: String, text: String, done: Boolean)

object EditRequest extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val format = jsonFormat3(EditRequest.apply)
}
