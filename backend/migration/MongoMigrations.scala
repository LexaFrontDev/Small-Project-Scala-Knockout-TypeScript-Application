import org.mongodb.scala._
import org.mongodb.scala.model.Indexes
import scala.concurrent.Await
import scala.concurrent.duration._

object MongoMigrations extends App {
  val mongoUri = sys.env.getOrElse("MONGO_URI", "mongodb://mongo:27017/mydb")
  val client: MongoClient = MongoClient(mongoUri)
  try {
    val db: MongoDatabase = client.getDatabase("mydb")
    val usersCollection: MongoCollection[Document] = db.getCollection("users")


    val futureIndex = usersCollection.createIndex(Indexes.ascending("email")).toFuture()
    Await.result(futureIndex, 10.seconds)

    println("Mongo migrations completed")
  } finally {
    client.close()
  }
}
