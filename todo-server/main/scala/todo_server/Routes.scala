package todo_server

import java.util.UUID

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.{Directive0, Directives, Route}
import todo_api.dto._
import todo_api.model.TodoItem
import todo_server.Extensions.RichFiniteDuration
import JsonSupport._

import scala.concurrent.duration.DurationInt
import scala.language.implicitConversions

trait Routes extends Directives {
  var data: List[TodoItem] = List.empty

  val getAllItems: Route = get {
    complete(data)
  }

  def getOneItem(id: String): Route = get {
    val item = data.find(_.id == id)
    item match {
      case Some(todo) => complete(todo)
      case None       => complete(StatusCodes.NotFound)
    }
  }

  val createItem: Route = put {
    entity(as[CreateRequest]) { createRequest =>
      val todo =
        TodoItem(UUID.randomUUID().toString, createRequest.text, done = false)
      data = data :+ todo

      complete(todo)
    }
  }

  val editItem: Route = post {
    entity(as[EditRequest]) { requestTodo =>
      if (!data.exists(_.id == requestTodo.id))
        complete(StatusCodes.NotFound)
      else {
        data = data.map {
          case oldTodo if oldTodo.id == requestTodo.id =>
            TodoItem(requestTodo)
          case otherTodo => otherTodo
        }
        complete("OK")
      }
    }
  }

  val editItems: Route = post {
    entity(as[BatchEditRequest]) { req =>
      val valid = req.items.map(_.id).forall(id => data.exists(_.id == id))
      if (!valid)
        complete(StatusCodes.NotFound)
      else {
        data = data.map {
          case model if req.items.map(_.id).contains(model.id) =>
            val current = req.items.find(_.id == model.id).get
            TodoItem(current)
          case otherTodo => otherTodo
        }
        complete("OK")
      }
    }
  }

  val deleteItem: Route = delete {
    entity(as[DeleteRequest]) { req =>
      if (!data.exists(_.id == req.id))
        complete(StatusCodes.NotFound)
      else {
        data = data.filter(_.id != req.id)
        complete(StatusCodes.OK)
      }
    }
  }

  val deleteItems: Route = delete {
    entity(as[BatchDeleteRequest]) { req =>
      val valid = req.ids.forall(id => data.exists(_.id == id))
      if (!valid)
        complete(StatusCodes.NotFound)
      else {
        data = data.filter(model => !req.ids.contains(model.id))
        complete(StatusCodes.OK)
      }
    }
  }

  def parameterizedDelay: Directive0 =
    parameter("delay".as[Int].?).flatMap(seconds =>
      onSuccess(seconds.getOrElse(0).seconds.await))
}
