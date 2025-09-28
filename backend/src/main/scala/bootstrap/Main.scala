package bootstrap

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpMethods._
import akka.http.scaladsl.model.headers.HttpOrigin
import ch.megard.akka.http.cors.scaladsl.CorsDirectives._
import ch.megard.akka.http.cors.scaladsl.settings.CorsSettings
import bootstrap.AppModule

object Main extends App with AppModule {
  implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "my-system")
  override implicit lazy val ec: scala.concurrent.ExecutionContext = system.executionContext

  val allowedOrigins = Set(
    "https://small-project-scala-knockout-typescript.onrender.com",
    "https://small-project-scala-knockout-typescript-62bw.onrender.com",
    "http://localhost:9090"
  )

  val settings = CorsSettings.defaultSettings
    .withAllowedOrigins { origin: HttpOrigin =>
      allowedOrigins.contains(origin.toString())
    }
    .withAllowedMethods(Seq(GET, POST, PUT, DELETE, OPTIONS))

  val routeWithCors = cors(settings) {
    usersController.routes
  }

  val bindingFuture = Http().newServerAt("0.0.0.0", 9000).bind(routeWithCors)
}
