package uz.targetsoftwaredevelopment.myapplication.data.remote.responses

import com.google.gson.annotations.SerializedName

data class MainPageDataResponse(
    val categories: List<CategoriesItem?>? = null,
    val statistics: Statistics? = null
)

data class CategoriesItem(
    val id: Int? = null,
    val name: String? = null,
    val createdAt: String? = null,
    @field:SerializedName("updated_at")
    val updayedAt: String? = null,
    val icon: String? = null
)

data class Statistics(
    val allVolunteers: Int? = null
)
