package todoApi

import akka.http.scaladsl.server.{HttpApp, Route}

object Main extends HttpApp with Routes with App {

  val routes: Route =
    parameterizedDelay {
      getAllItems ~
        getOneItem ~
        path("item") { createItem ~ editItem ~ deleteItem }
    }

  startServer("localhost", 9000)
}
