package todoApi

import java.util.UUID

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.{Directive0, Directives, Route}
import todoApi.Extensions.RichFiniteDuration
import todoApi.dto._

import scala.concurrent.duration.DurationInt

trait Routes extends Directives {
  var data: List[EditRequest] = List.empty

  val getAllItems: Route = path("items") {
    get {
      complete(data)
    }
  }
  val getOneItem: Route = path("item" / Remaining) { id =>
    val item = data.find(_.id == id)
    item match {
      case Some(todo) => complete(todo)
      case None       => complete(StatusCodes.NotFound)
    }
  }
  val createItem: Route = post {
    entity(as[CreateRequest]) { createRequest =>
      val todo = EditRequest(UUID.randomUUID().toString,
                             createRequest.text,
                             createRequest.done)
      data = data :+ todo

      complete(todo)
    }
  }
  val editItem: Route = put {
    entity(as[EditRequest]) { requestTodo =>
      if (!data.exists(_.id == requestTodo.id))
        complete(StatusCodes.NotFound)
      else {
        data = data.map {
          case oldTodo if oldTodo.id == requestTodo.id => requestTodo
          case otherTodo                               => otherTodo
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

  def parameterizedDelay: Directive0 =
    parameter("delay".as[Int].?).flatMap(seconds =>
      onSuccess(seconds.getOrElse(0).seconds.await))
}
