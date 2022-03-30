package uz.targetsoftwaredevelopment.wsen.data.remote.responses

import java.io.Serializable

data class UserDataResponse(
    val last_name: String? = null,
    val photo: String? = null,
    val first_name: String? = null,
    val email: String? = null,
    val username: String? = null
) : Serializable
