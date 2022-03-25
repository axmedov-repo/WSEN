package uz.targetsoftwaredevelopment.myapplication.data.remote.responses

import com.google.gson.annotations.SerializedName

data class AddVideoResponse(
    val owner: Int? = null,
    val preloadImg: String? = null,
    val like: Int? = null,
    val dislike: Int? = null,
    val createdAt: String? = null,
    val video: String? = null,
    val title: String? = null,
    val chatLink: String? = null,
    @field:SerializedName("updatedAt")
    val updayedAt: String? = null,
    val location: String? = null,
    val id: Int? = null,
    val category: Int? = null,
    val status: String? = null,
    val desc: String? = null
)
