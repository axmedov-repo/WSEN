package uz.targetsoftwaredevelopment.myapplication.data.remote.responses

data class LoginUserResponse(
    val token: String? = null,
    val user: User? = null
)

data class User(
    val last_name: String? = null,
    val id: Int? = null,
    val first_name: String? = null,
    val email: String? = null,
    val username: String? = null
)
