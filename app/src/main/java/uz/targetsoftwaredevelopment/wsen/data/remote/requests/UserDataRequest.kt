package uz.targetsoftwaredevelopment.wsen.data.remote.requests

import java.io.File
import java.io.Serializable

data class UserDataRequest(
    val last_name: String? = null,
    val photo: File? = null,
    val first_name: String? = null,
    val email: String? = null,
    val username: String? = null
) : Serializable
