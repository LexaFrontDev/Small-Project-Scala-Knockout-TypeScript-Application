object MongoMigrations extends App {
  val mongoUri = sys.env.getOrElse("MONGO_URI", "mongodb://mongo:27017/mydb")
  val client: MongoClient = MongoClient(mongoUri)
  try {
    val db: MongoDatabase = client.getDatabase("mydb")
    val usersCollection: MongoCollection[Document] = db.getCollection("users")
    usersCollection.createIndex(Document("email" -> 1)).results()
    println("Mongo migrations completed")
  } finally {
    client.close()
  }
}
