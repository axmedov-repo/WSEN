package uz.targetsoftwaredevelopment.wsen.data.remote.requests

import java.io.Serializable

data class UserData(
    val last_name: String? = null,
    val photo: String? = null,
    val first_name: String? = null,
    val email: String? = null,
    val username: String? = null
) : Serializable
