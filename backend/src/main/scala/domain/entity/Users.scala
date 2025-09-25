package domain.entity

case class Users(
                 id: Option[String] = None,
                 name: String,
                 email: String,
                 subscribe: Boolean,
                 role: String
)
