package uz.targetsoftwaredevelopment.myapplication.data.remote.responses

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class VideoData(
    val owner: Owner? = null,
    val preload_img: String? = null,
    val like: Int? = null,
    val dislike: Int? = null,
    val created_at: String? = null,
    val liked_user: List<Any?>? = null,
    val title: String? = null,
    val chat_link: String? = null,
    val disliked_user: List<Any?>? = null,
    @field:SerializedName("updatedAt")
    val updayed_at: String? = null,
    val location: String? = null,
    val id: Int? = null,
    val category: Int? = null,
    val status: String? = null,
    val video: String? = null,
    val desc: String? = null,
    val isLikedByCurrentUser: Boolean = false
) : Serializable