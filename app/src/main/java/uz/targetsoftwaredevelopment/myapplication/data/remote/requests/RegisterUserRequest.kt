package uz.targetsoftwaredevelopment.myapplication.data.remote.requests

import java.io.File

data class RegisterUserRequest(
    val username: String? = null,
    val password: String? = null,
    val confirm: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val photo: File? = null,
    val email: String? = null
)
