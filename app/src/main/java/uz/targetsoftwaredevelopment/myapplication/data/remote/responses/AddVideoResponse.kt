package uz.targetsoftwaredevelopment.myapplication.data.remote.responses

import com.google.gson.annotations.SerializedName

data class AddVideoResponse(
    val id: Int? = null,
    val like: Int? = null,
    val dislike: Int? = null,
    val createdAt: String? = null,
    @field:SerializedName("updatedAt")
    val updayedAt: String? = null,
    val status: String? = null,
    val video: String? = null,
    val preloadImg: String? = null,
    val title: String? = null,
    val location: String? = null,
    val desc: String? = null,
    val chatLink: String? = null,
    val owner: Int? = null,
    val category: Int? = null
)
