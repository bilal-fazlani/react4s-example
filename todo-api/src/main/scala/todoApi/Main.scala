package todoApi

import java.util.UUID

import akka.actor.ActorSystem
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.{Directive0, HttpApp, Route}
import akka.stream.ActorMaterializer
import todoApi.Extensions.RichFiniteDuration

import scala.concurrent.ExecutionContext
import scala.concurrent.duration.{DurationInt, FiniteDuration}

object Main extends HttpApp with App {
  implicit val system: ActorSystem = ActorSystem("my-system")
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  // needed for the future flatMap/onComplete in the end
  implicit val executionContext: ExecutionContext = system.dispatcher

  var data: List[Todo] = List.empty

  def after(delay: FiniteDuration): Directive0 = onSuccess(delay.await)

  import CreateRequest._
  import Todo._

  val routes: Route =
    parameter("delay".as[Int].?) { delay =>
      after(delay.getOrElse(0).seconds) {
        (path("items") & get) {
          complete(data)
        } ~
          path("item" / Remaining) { id =>
            val item = data.find(_.id == id)
            item match {
              case Some(todo) => complete(todo)
              case None       => complete(StatusCodes.NotFound)
            }
          } ~ path("item") {
          post {
            entity(as[CreateRequest]) { createRequest =>
              val todo = Todo(UUID.randomUUID().toString,
                              createRequest.text,
                              createRequest.done)
              data = data :+ todo

              complete(todo)
            }
          } ~
            put {
              entity(as[Todo]) { newTodo =>
                if (!data.exists(_.id == newTodo.id))
                  complete(StatusCodes.NotFound)
                else {
                  data = data.map {
                    case oldTodo if oldTodo.id == newTodo.id => newTodo
                    case otherTodo                           => otherTodo
                  }
                  complete("OK")
                }
              }
            } ~
            delete {
              entity(as[DeleteRequest]) { req =>
                if (!data.exists(_.id == req.id))
                  complete(StatusCodes.NotFound)
                else {
                  data = data.filter(_.id != req.id)
                  complete(StatusCodes.OK)
                }
              }
            }
        }
      }
    }

  startServer("localhost", 9000)
}
