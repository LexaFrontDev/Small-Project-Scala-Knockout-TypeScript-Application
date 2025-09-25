package infrastructure.EntityMapper.users

import org.mongodb.scala.bson.collection.immutable.Document
import org.mongodb.scala.bson.BsonObjectId
import domain.entity.Users

object UserMapper {

  def toDocument(user: Users): Document = {
    val base = Document(
      "name"      -> user.name,
      "email"     -> user.email,
      "subscribe" -> user.subscribe,
      "role"      -> user.role
    )


    user.id.map(id => base + ("_id" -> BsonObjectId(id))).getOrElse(base)
  }

  def fromDocument(doc: Document): Users = {
    Users(
      id        = doc.get("_id").collect { case oid: BsonObjectId => oid.getValue.toHexString },
      name      = doc.get("name").collect { case s: String => s }.getOrElse(""),
      email     = doc.get("email").collect { case s: String => s }.getOrElse(""),
      subscribe = doc.get("subscribe").collect { case b: Boolean => b }.getOrElse(false),
      role      = doc.get("role").collect { case s: String => s }.getOrElse("")
    )
  }
}
