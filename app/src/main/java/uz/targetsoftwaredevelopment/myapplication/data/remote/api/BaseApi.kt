package uz.targetsoftwaredevelopment.myapplication.data.remote.api

import retrofit2.Response
import retrofit2.http.*
import uz.targetsoftwaredevelopment.myapplication.data.remote.requests.*
import uz.targetsoftwaredevelopment.myapplication.data.remote.responses.*

interface BaseApi {
    @POST("client/")
    suspend fun registerUser(@Body data: RegisterUserRequest): Response<RegisterUserResponse>

    @POST("client/login/")
    suspend fun loginUser(@Body data: LoginUserRequest): Response<LoginUserResponse>

    @GET("api/")
    suspend fun getMainPageData(): Response<MainPageDataResponse>

    @GET("api/all-posts/")
    suspend fun getAllVideos(): Response<AllVideosResponse>

    @POST("api/my-post/")
    suspend fun addVideo(@Body data: AddVideoRequest): Response<AddVideoResponse>

    // client/me/userni id si/
    @GET
    suspend fun getUserData(@Url url: String): Response<UserData>

    // client/me/userni id si/
    @PUT
    suspend fun editUserData(@Url url: String, @Body data: UserData): Response<UserData>

    // api/my-post/<int:post_id>/
    @PUT
    suspend fun editMyVideo(
        @Url url: String,
        @Body data: EditVideoRequest
    ): Response<EditVideoResponse>

    // api/my-post/<int:post_id>/
    @GET
    suspend fun getMyVideo(@Url url: String): Response<VideoData>

    @GET("api/my-post/")
    suspend fun getAllMyVideos(): Response<AllMyVideosResponse>

    // api/my-post/<int:post_id>/
    @DELETE
    suspend fun deleteMyVideo(@Url url: String): Response<String>

    // api/post-read-only/<int:post_id>/
    @GET
    suspend fun getWatchVideoData(@Url url: String): Response<GetWatchVideoResponse>

    @GET("api/wished-posts/")
    suspend fun getAllFavouriteVideos(): Response<AllFavouriteVideosResponse>

    // api/wish-event/post ni id si/
    @POST  // xuddi shu idi ga yana murojaat qilsa like yo'qoladi
    suspend fun likeVideo(@Url url: String): Response<LikeVideoResponse>

    @POST("api/spam/")
    suspend fun spamVideo(@Body data: SpamVideoRequest): Response<SpamVideoResponse>
}
