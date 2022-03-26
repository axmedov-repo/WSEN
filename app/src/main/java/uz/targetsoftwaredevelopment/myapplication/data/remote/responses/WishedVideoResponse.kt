package uz.targetsoftwaredevelopment.myapplication.data.remote.responses

import com.google.gson.annotations.SerializedName

data class WishedVideoResponse(
	val next: Any? = null,
	val previous: Any? = null,
	val count: Int? = null,
	val results: Results? = null
)

data class DataItem(
	val owner: Owner? = null,
	val preload_mg: String? = null,
	val like: Int? = null,
	val dislike: Int? = null,
	val created_at: String? = null,
	val video: String? = null,
	val liked_user: List<Int?>? = null,
	val title: String? = null,
	val chatLink: Any? = null,
	val disliked_user: List<Any?>? = null,
	@field:SerializedName("updayed_at")
	val updayed_at: String? = null,
	val location: String? = null,
	val id: Int? = null,
	val category: Int? = null,
	val status: String? = null,
	val desc: String? = null
)

data class Results(
	val data: List<DataItem?>? = null,
	val page_size: Int? = null
)
