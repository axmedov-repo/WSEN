package uz.targetsoftwaredevelopment.myapplication.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.targetsoftwaredevelopment.myapplication.data.enums.SplashOpenScreenTypes
import uz.targetsoftwaredevelopment.myapplication.data.remote.requests.AddVideoRequest
import uz.targetsoftwaredevelopment.myapplication.data.remote.requests.LoginUserRequest
import uz.targetsoftwaredevelopment.myapplication.data.remote.requests.RegisterUserRequest
import uz.targetsoftwaredevelopment.myapplication.data.remote.responses.*

interface BaseRepository {

    fun getSplashOpenScreen(): SplashOpenScreenTypes

    fun setSplashOpenScreen(type: SplashOpenScreenTypes)

    fun registerUser(data: RegisterUserRequest): Flow<Result<RegisterUserResponse?>>

    fun loginUser(data: LoginUserRequest): Flow<Result<LoginUserResponse?>>

    fun getMainPageData(): Flow<Result<MainPageDataResponse?>>

    fun getAllVideos(): Flow<Result<List<VideoData?>?>>

    fun addVideo(data: AddVideoRequest): Flow<Result<AddVideoResponse?>>
}
