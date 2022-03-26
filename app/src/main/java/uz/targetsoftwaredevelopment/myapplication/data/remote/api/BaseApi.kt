package uz.targetsoftwaredevelopment.myapplication.data.remote.api

import retrofit2.Response
import retrofit2.http.*
import uz.targetsoftwaredevelopment.myapplication.data.remote.requests.*
import uz.targetsoftwaredevelopment.myapplication.data.remote.responses.*

interface BaseApi {
    @POST("en/client/")
    suspend fun registerUser(@Body data: RegisterUserRequest): Response<RegisterUserResponse>

    @POST("en/client/login/")
    suspend fun loginUser(@Body data: LoginUserRequest): Response<LoginUserResponse>

    @GET("en/api/")
    suspend fun getMainPageData(): Response<MainPageDataResponse>

    @GET("en/api/all-posts/")
    suspend fun getAllVideos(): Response<AllVideosResponse>

    @POST("en/api/my-post/")
    suspend fun addVideo(@Body data: AddVideoRequest): Response<AddVideoResponse>

    // uz/client/me/userni id si/
    @GET
    suspend fun getUserData(@Url url: String): Response<UserData>

    // uz/client/me/userni id si/
    @PUT
    suspend fun editUserData(@Url url: String, @Body data: UserData): Response<UserData>

    //uz/api/my-post/<int:post_id>/
    @PUT
    suspend fun editMyVideo(
        @Url url: String,
        @Body data: EditVideoRequest
    ): Response<EditVideoResponse>

    //lang/api/my-post/<int:post_id>/
    @GET
    suspend fun getMyVideo(@Url url: String): Response<VideoData>

    //lang/api/my-post/<int:post_id>/
    @DELETE
    suspend fun deleteMyVideo(@Url url: String): Response<String>

    //lang/api/post-read-only/<int:post_id>/
    @GET
    suspend fun getWatchVideoData(@Url url: String): Response<GetWatchVideoResponse>

    //lang/api/wished_posts
    @GET
    suspend fun getWishedPosts(@Url url: String): Response<WishedVideoResponse>

    @POST("uz/api/wish-event/post ni id si/") // xuddi shu idi ga yana murojaat qilsa like yo#qoladi
    suspend fun likeVideo(): Response<LikeVideoResponse>

    @POST("uz/api/spam/")
    suspend fun spamVideo(@Body data: SpamVideoRequest): Response<SpamVideoResponse>
}