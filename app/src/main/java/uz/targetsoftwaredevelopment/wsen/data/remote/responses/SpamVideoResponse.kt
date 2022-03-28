package uz.targetsoftwaredevelopment.wsen.data.remote.responses

import com.google.gson.annotations.SerializedName

data class SpamVideoResponse(
    val post: Int? = null,
    val created_at: String? = null,
    @field:SerializedName("updayed_at")
    val updayed_at: String? = null,
    val id: Int? = null,
    val user: Int? = null,
    val desc: String? = null
)
