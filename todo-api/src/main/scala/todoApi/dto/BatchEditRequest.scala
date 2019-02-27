package todoApi.dto

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol

case class BatchEditRequest(items: List[EditRequest])

object BatchEditRequest extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val format = jsonFormat1(BatchEditRequest.apply)
}