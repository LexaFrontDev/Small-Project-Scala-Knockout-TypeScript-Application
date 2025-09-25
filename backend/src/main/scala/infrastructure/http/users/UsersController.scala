package infrastructure.http.users

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import domain.entity.Users
import domain.persistence.repository.users.UsersRepository
import scala.concurrent.ExecutionContext

class UsersController(userRepository: UsersRepository)(implicit ec: ExecutionContext)
  extends infrastructure.http.json.JsonFormats {

  val routes: Route =
    pathPrefix("users") {
      concat(
        pathEndOrSingleSlash {
          get {
            onSuccess(userRepository.findAll()) { users =>
              complete(users)
            }
          }
        },
        path("save") {
          post {
            entity(as[Users]) { user =>
              onSuccess(userRepository.save(user)) {
                case Right(savedUser) => complete(StatusCodes.Created -> savedUser)
                case Left(error)      => complete(StatusCodes.BadRequest -> error)
              }
            }
          }
        }
      )
    }
}
