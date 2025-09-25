package infrastructure.http.json

import spray.json._
import domain.entity.Users

trait JsonFormats extends DefaultJsonProtocol {
  implicit val usersFormat: RootJsonFormat[Users] = jsonFormat5(Users)
}
