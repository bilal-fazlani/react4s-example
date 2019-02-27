package todoApi

import akka.http.scaladsl.server.{HttpApp, Route}

object Main extends HttpApp with Routes with App {

  val routes: Route =
    parameterizedDelay {
      path("items") {
        getAllItems ~ deleteItems ~ editItems
      } ~
        path("item" / Remaining) { id =>
          getOneItem(id)
        } ~
        path("item") {
          createItem ~ editItem ~ deleteItem
        }
    }

  startServer("localhost", 9000)
}
