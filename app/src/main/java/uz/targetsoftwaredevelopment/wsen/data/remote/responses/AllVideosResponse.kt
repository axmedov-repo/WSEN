package uz.targetsoftwaredevelopment.wsen.data.remote.responses

data class AllVideosResponse(
    val next: String? = null,
    val previous: String? = null,
    val count: Int? = null,
    val results: List<VideoData?>? = null
)


