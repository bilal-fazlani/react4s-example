package todo_server

import akka.http.scaladsl.model.HttpMethods._
import akka.http.scaladsl.model.headers.{HttpOrigin, HttpOriginRange}
import akka.http.scaladsl.server.{HttpApp, Route}
import ch.megard.akka.http.cors.scaladsl.CorsDirectives
import ch.megard.akka.http.cors.scaladsl.settings.CorsSettings

object Main extends HttpApp with Routes with App {

  val settings =
    CorsSettings.defaultSettings
      .withAllowedMethods(List(POST, PUT, GET, POST, OPTIONS))
      .withAllowedOrigins(HttpOriginRange(HttpOrigin("http://localhost:8080")))

  val routes: Route =
    handleRejections(CorsDirectives.corsRejectionHandler) {
      CorsDirectives.cors(settings) {
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
      }
    }

  startServer("localhost", 9000)
}
