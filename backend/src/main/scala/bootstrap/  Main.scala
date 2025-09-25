package bootstrap

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.Materializer
import bootstrap.AppModule

object Main extends App with AppModule {
  implicit val system: ActorSystem = ActorSystem("my-system")
  implicit val mat: akka.stream.Materializer = Materializer(system)
  implicit val ec: scala.concurrent.ExecutionContext = scala.concurrent.ExecutionContext.global


  val bindingFuture = Http().newServerAt("0.0.0.0", 9000).bind(usersController.routes)
}
