package uz.targetsoftwaredevelopment.myapplication.data.remote.responses

data class RegisterUserResponse(
    val username: String? = null,
    val first_name: String? = null,
    val last_name: String? = null,
    val photo: Any? = null,
    val email: String? = null
)