package uz.targetsoftwaredevelopment.wsen.data.remote.responses

import com.google.gson.annotations.SerializedName

data class MainPageDataResponse(
    val categories: List<CategoriesItem?>? = null,
    val statistics: Statistics? = null
)

data class CategoriesItem(
    val id: Int? = null,
    val name: String? = null,
    val created_at: String? = null,
    @field:SerializedName("updated_at")
    val updayed_at: String? = null,
    val icon: String? = null
)

data class Statistics(
    val all_volunteers: Int? = 0
)
