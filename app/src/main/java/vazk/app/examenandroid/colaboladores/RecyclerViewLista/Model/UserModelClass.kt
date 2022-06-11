package vazk.app.examplejson.Model

 data class Users(
     val data: ArrayList<UserModelClass>
)

data class UserModelClass(
    val id: String,
    val location: Location,
    val mail: String,
    val name: String
)

data class Location(
    val lat: String,
    val log: String
)