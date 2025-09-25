package bootstrap

import com.softwaremill.macwire._
import infrastructure.persistence.repository.users.MongoUsersRepository
import infrastructure.http.users.UsersController
import domain.persistence.repository.users.UsersRepository
import scala.concurrent.ExecutionContext
import infrastructure.http.json
import domain.entity.Users
import spray.json._
import infrastructure.http.json.JsonFormats

trait AppModule extends JsonFormats {
  implicit lazy val ec: ExecutionContext = scala.concurrent.ExecutionContext.global
  lazy val usersRepository: UsersRepository = wire[MongoUsersRepository]
  lazy val usersController: UsersController   = wire[UsersController]
}

