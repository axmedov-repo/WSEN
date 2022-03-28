package uz.targetsoftwaredevelopment.wsen.data.remote.requests

import com.google.gson.annotations.SerializedName

data class EditVideoRequest(
    val like: Int? = null,
    val dislike: Int? = null,
    val created_at: String? = null,
    @field:SerializedName("updated_at")
    val updayed_at: String? = null,
    val location: String? = null,
    val id: Int? = null,
    val title: String? = null,
    val chat_link: String? = null,
    val category: Int? = null,
    val status: String? = null,
    val desc: String? = null
)
