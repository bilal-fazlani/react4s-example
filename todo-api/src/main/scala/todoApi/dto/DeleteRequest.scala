package todoApi.dto

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol

case class DeleteRequest(id: String)

object DeleteRequest extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val format = jsonFormat1(DeleteRequest.apply)
}
