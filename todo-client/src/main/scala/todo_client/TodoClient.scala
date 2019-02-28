package todo_client

import monix.execution.Scheduler.Implicits.global
import todo_api.dto._
import JsonSupport._
import fr.hmil.roshttp.{HttpRequest, Method}
import fr.hmil.roshttp.body.PlainTextBody
import todo_api.model.TodoItem
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
      .withHeader("content-type", "application/json")
      .post(PlainTextBody(write(req)))
      .map(_ => ())
  }

  def modify(req: BatchEditRequest): Future[Unit] = {
    val request = HttpRequest(s"$url/items")
    request
      .withHeader("content-type", "application/json")
      .post(PlainTextBody(write(req)))
      .map(_ => ())
  }

  def create(req: CreateRequest): Future[TodoItem] = {
    val request = HttpRequest(s"$url/item")
    request
      .withHeader("content-type", "application/json")
      .put(PlainTextBody(write(req)))
      .map(r => read[TodoItem](r.body))
  }

  def delete(req: DeleteRequest): Future[Unit] = {
    val request = HttpRequest(s"$url/item")
    request
      .withHeader("content-type", "application/json")
      .withMethod(Method.DELETE)
      .withBody(PlainTextBody(write(req)))
      .send()
      .map(_ => ())
  }

  def delete(req: BatchDeleteRequest): Future[Unit] = {
    val request = HttpRequest(s"$url/items")
    request
      .withHeader("content-type", "application/json")
      .withMethod(Method.DELETE)
      .withBody(PlainTextBody(write(req)))
      .send()
      .map(_ => ())
  }
}
