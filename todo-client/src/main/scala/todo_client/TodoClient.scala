package todo_client

import fr.hmil.roshttp.{HttpRequest, Method}
import monix.execution.Scheduler.Implicits.global
import todo_api.dto._
import todo_api.model.TodoItem
import todo_client.Extensions._
import todo_client.JsonSupport._
import upickle.default._

import scala.concurrent.Future

class TodoClient(url: String = "http://localhost:9000") {

  def getAll: Future[List[TodoItem]] = {
    val request = HttpRequest(url)
    request.send().map(res => read[List[TodoItem]](res.body))
  }

  def modify(req: EditRequest): Future[Unit] = {
    val request = HttpRequest(s"$url/item")
    request
      .post(JsonTextBody(write(req)))
      .map(_ => ())
  }

  def modify(req: BatchEditRequest): Future[Unit] = {
    val request = HttpRequest(s"$url/items")
    request
      .post(JsonTextBody(write(req)))
      .map(_ => ())
  }

  def create(req: CreateRequest): Future[TodoItem] = {
    val request = HttpRequest(s"$url/item")
    request
      .put(JsonTextBody(write(req)))
      .map(r => read[TodoItem](r.body))
  }

  def delete(req: DeleteRequest): Future[Unit] = {
    val request = HttpRequest(s"$url/item")
    request
      .withMethod(Method.DELETE)
      .withJsonStringBody(req)
      .send()
      .map(_ => ())
  }

  def delete(req: BatchDeleteRequest): Future[Unit] = {
    val request = HttpRequest(s"$url/items")
    request
      .withMethod(Method.DELETE)
      .withBody(JsonTextBody(write(req)))
      .send()
      .map(_ => ())
  }
}
