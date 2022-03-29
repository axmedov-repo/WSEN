package uz.targetsoftwaredevelopment.wsen.data.remote.responses

import com.google.gson.annotations.SerializedName

data class AddVideoResponse(
    val id: Int? = null,
    val like: Int? = null,
    val dislike: Int? = null,
    val created_at: String? = null,
    @field:SerializedName("updatedAt")
    val updayed_at: String? = null,
    val status: String? = null,
    val video: String? = null,
    val preload_mg: String? = null,
    val title: String? = null,
    val location: String? = null,
    val desc: String? = null,
    val chat_ink: String? = null,
    val owner: Int? = null,
    val category: Int? = null
)
