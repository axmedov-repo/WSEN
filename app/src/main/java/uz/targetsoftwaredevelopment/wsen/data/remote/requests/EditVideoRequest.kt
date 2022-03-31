package uz.targetsoftwaredevelopment.wsen.data.remote.requests

import com.google.gson.annotations.SerializedName

data class EditVideoRequest(
    val location: String? = null,
    val title: String? = null,
    val desc: String? = null,
    val category: Int? = 1,
    val id: Int? = null
)
