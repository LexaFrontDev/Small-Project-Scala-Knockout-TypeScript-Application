package infrastructure.persistence.repository.users

import domain.entity.Users
import infrastructure.EntityMapper.users.UserMapper
import org.mongodb.scala._
import org.mongodb.scala.model.Indexes
import domain.persistence.repository.users.UsersRepository
import scala.concurrent.{ExecutionContext, Future}

class MongoUsersRepository(implicit ec: ExecutionContext) extends UsersRepository {

  private val mongoUri: String = sys.env.getOrElse("MONGO_URI", "mongodb://localhost:27017/mydb")

  private val databaseName: String = {
    val fromEnv = sys.env.get("MONGO_DB_NAME")
    fromEnv.getOrElse {
      val dbFromUri = mongoUri.split("/").lastOption.getOrElse("mydb")
      if (dbFromUri.contains("?")) dbFromUri.takeWhile(_ != '?') else dbFromUri
    }
  }

  private val client: MongoClient = MongoClient(mongoUri)
  private val db: MongoDatabase   = client.getDatabase(databaseName)
  private val usersCollection: MongoCollection[Document] = db.getCollection("users")


  usersCollection.createIndex(Indexes.ascending("email")).toFuture()

  override def save(user: Users): Future[Either[String, Users]] = {
    val doc = UserMapper.toDocument(user)

    usersCollection
      .insertOne(doc)
      .toFuture()
      .map(_ => Right(user))
      .recover {
        case e: Throwable if e.getMessage.contains("E11000") =>
          Left("Email already exists")
        case e: Throwable =>
          Left(s"Database error: ${e.getMessage}")
      }
  }

  override def findAll(): Future[Seq[Users]] = {
    usersCollection
      .find()
      .sort(Document("_id" -> -1))
      .toFuture()
      .map(_.map(UserMapper.fromDocument))
  }

  def close(): Unit = client.close()
}
