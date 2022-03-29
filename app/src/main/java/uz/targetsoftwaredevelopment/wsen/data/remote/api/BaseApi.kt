package uz.targetsoftwaredevelopment.wsen.data.remote.api

import retrofit2.Response
import retrofit2.http.*
import uz.targetsoftwaredevelopment.wsen.data.remote.requests.*
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.*

interface BaseApi {
    @POST("client/")
    suspend fun registerUser(@Body data: RegisterUserRequest): Response<RegisterUserResponse>

    @POST("client/login/")
    suspend fun loginUser(@Body data: LoginUserRequest): Response<LoginUserResponse>

    @DELETE("client/logout/")
    suspend fun logoutUser(): Response<LogoutResponse>

    @GET("api/")
    suspend fun getMainPageData(@Header("Authorization") token: String): Response<MainPageDataResponse>

    @GET("api/all-posts/")
    suspend fun getAllVideos(@Header("Authorization") token: String): Response<AllVideosResponse>

    @POST("api/my-post/")
    suspend fun addVideo(
        @Header("Authorization") token: String,
        @Body data: AddVideoRequest
    ): Response<AddVideoResponse>

    // client/me/userni id si/
    @GET
    suspend fun getUserData(
        @Header("Authorization") token: String,
        @Url url: String
    ): Response<UserData>

    // client/me/userni id si/
    @PUT
    suspend fun editUserData(
        @Header("Authorization") token: String,
        @Url url: String,
        @Body data: UserData
    ): Response<UserData>

    // api/my-post/<int:post_id>/
    @PUT
    suspend fun editMyVideo(
        @Header("Authorization") token: String,
        @Url url: String,
        @Body data: EditVideoRequest
    ): Response<EditVideoResponse>

    // api/my-post/<int:post_id>/
    @GET
    suspend fun getMyVideo(
        @Header("Authorization") token: String,
        @Url url: String
    ): Response<VideoData>

    @GET("api/my-post/")
    suspend fun getAllMyVideos(@Header("Authorization") token: String): Response<AllMyVideosResponse>

    // api/my-post/<int:post_id>/
    @DELETE
    suspend fun deleteMyVideo(
        @Header("Authorization") token: String,
        @Url url: String
    ): Response<String>

    // api/post-read-only/<int:post_id>/
    @GET
    suspend fun getWatchVideoData(
        @Header("Authorization") token: String,
        @Url url: String
    ): Response<GetWatchVideoResponse>

    @GET("api/wished-posts/")
    suspend fun getAllFavouriteVideos(@Header("Authorization") token: String): Response<AllFavouriteVideosResponse>

    // api/wish-event/post ni id si/
    @POST  // xuddi shu idi ga yana murojaat qilsa like yo'qoladi
    suspend fun likeVideo(
        @Header("Authorization") token: String,
        @Url url: String
    ): Response<LikeVideoResponse>

    @POST("api/spam/")
    suspend fun spamVideo(
        @Header("Authorization") token: String,
        @Body data: SpamVideoRequest
    ): Response<SpamVideoResponse>
}
