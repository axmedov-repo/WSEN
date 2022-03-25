package uz.targetsoftwaredevelopment.myapplication.data.remote.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import uz.targetsoftwaredevelopment.myapplication.data.remote.requests.AddVideoRequest
import uz.targetsoftwaredevelopment.myapplication.data.remote.requests.LoginUserRequest
import uz.targetsoftwaredevelopment.myapplication.data.remote.requests.RegisterUserRequest
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
}