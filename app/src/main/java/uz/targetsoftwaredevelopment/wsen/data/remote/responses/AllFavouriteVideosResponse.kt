package uz.targetsoftwaredevelopment.wsen.data.remote.responses

data class AllFavouriteVideosResponse(
    val next: Any? = null,
    val previous: Any? = null,
    val count: Int? = null,
    val results: Results? = null
)

data class Results(
    val data: List<VideoData?>? = null,
    val page_size: Int? = null
)
