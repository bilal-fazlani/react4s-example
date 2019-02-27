package todoApi.dto

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol

case class BatchDeleteRequest(ids: List[String])

object BatchDeleteRequest extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val format = jsonFormat1(BatchDeleteRequest.apply)
}