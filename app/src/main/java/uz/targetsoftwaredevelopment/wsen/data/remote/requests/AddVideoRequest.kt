package uz.targetsoftwaredevelopment.wsen.data.remote.requests

import java.io.File

data class AddVideoRequest(
    val video: File? = null,
    val title: String? = null,
    val location: String? = null,
    val desc: String? = null,
    val category: Int? = 1,
)
