package uz.targetsoftwaredevelopment.myapplication.data.remote.responses

import com.google.gson.annotations.SerializedName

data class AllVideosResponse(
    val next: Any? = null,
    val previous: Any? = null,
    val count: Int? = null,
    val results: List<VideoData?>? = null
)

data class VideoData(
    val owner: Owner? = null,
    val preloadImg: String? = null,
    val like: Int? = null,
    val dislike: Int? = null,
    val createdAt: String? = null,
    val likedUser: List<Any?>? = null,
    val title: String? = null,
    val chatLink: String? = null,
    val dislikedUser: List<Any?>? = null,
    @field:SerializedName("updatedAt")
    val updayedAt: String? = null,
    val location: String? = null,
    val id: Int? = null,
    val category: Int? = null,
    val status: String? = null,
    val desc: String? = null
)

data class Owner(
    val lastName: String? = null,
    val photo: Any? = null,
    val firstName: String? = null,
    val email: String? = null,
    val username: String? = null
)
