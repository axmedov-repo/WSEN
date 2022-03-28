package uz.targetsoftwaredevelopment.wsen.data.remote.responses

import com.google.gson.annotations.SerializedName

data class EditVideoResponse(
	val owner: Int? = null,
	val preload_img: String? = null,
	val like: Int? = null,
	val dislike: Int? = null,
	val created_at: String? = null,
	val video: String? = null,
	val title: String? = null,
	val chat_link: String? = null,
	@field:SerializedName("updated_at")
	val updayed_at: String? = null,
	val location: String? = null,
	val id: Int? = null,
	val category: Int? = null,
	val status: String? = null,
	val desc: String? = null
)
