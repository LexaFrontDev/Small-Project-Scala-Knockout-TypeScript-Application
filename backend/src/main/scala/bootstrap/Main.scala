package bootstrap

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import ch.megard.akka.http.cors.scaladsl.CorsDirectives._
import bootstrap.AppModule

object Main extends App with AppModule {
  implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "my-system")
  override implicit lazy val ec: scala.concurrent.ExecutionContext = system.executionContext


  val routeWithCors = cors() {
    usersController.routes
  }

  val bindingFuture = Http().newServerAt("0.0.0.0", 9000).bind(routeWithCors)
}