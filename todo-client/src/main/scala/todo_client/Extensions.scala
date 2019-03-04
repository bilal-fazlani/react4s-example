package todo_client

import java.nio.ByteBuffer

import fr.hmil.roshttp.HttpRequest
import fr.hmil.roshttp.body.BulkBodyPart
import upickle.default._

class JsonTextBody private (text: String) extends BulkBodyPart {
  override def contentType: String = "application/json; charset=utf-8"
  override def contentData: ByteBuffer =
    ByteBuffer.wrap(text.getBytes("utf-8"))
}

object JsonTextBody {
  def apply(text: String): JsonTextBody =
    new JsonTextBody(text)
}

object Extensions {
  implicit class RichHttpRequest(value: HttpRequest) {
    def withJsonStringBody[T: Writer](req: T): HttpRequest =
      value.withBody(JsonTextBody(write(req)))
  }
}
