package uz.targetsoftwaredevelopment.wsen.data.remote.responses

data class AllMyVideosResponse(
    val next: Any? = null,
    val previous: Any? = null,
    val count: Int? = null,
    val results: List<VideoData?>? = null
)

