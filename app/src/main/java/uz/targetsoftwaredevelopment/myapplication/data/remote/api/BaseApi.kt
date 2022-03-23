package uz.targetsoftwaredevelopment.myapplication.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import uz.targetsoftwaredevelopment.myapplication.data.remote.responses.MainPageDataResponse

interface BaseApi {
    @GET("en/api/")
    suspend fun getMainPageData() : Response<MainPageDataResponse>

}