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
      id = doc.get("_id").collect { case oid: BsonObjectId => oid.getValue.toHexString },
      name = doc.get("name").map(_.asString().getValue).getOrElse(""),
      email = doc.get("email").map(_.asString().getValue).getOrElse(""),
      subscribe = doc.get("subscribe").exists(_.asBoolean().getValue),
      role = doc.get("role").map(_.asString().getValue).getOrElse("")
    )
  }

}
