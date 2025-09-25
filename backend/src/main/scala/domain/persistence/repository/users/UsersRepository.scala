package domain.persistence.repository.users
import domain.entity.Users
import scala.concurrent.Future

trait UsersRepository {
  def save(user: Users): Future[Either[String, Users]]
  def findAll(): Future[Seq[Users]]
}