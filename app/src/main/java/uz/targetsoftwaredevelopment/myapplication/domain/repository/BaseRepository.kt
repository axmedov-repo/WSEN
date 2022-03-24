package uz.targetsoftwaredevelopment.myapplication.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.targetsoftwaredevelopment.myapplication.data.remote.requests.LoginUserRequest
import uz.targetsoftwaredevelopment.myapplication.data.remote.requests.RegisterUserRequest
import uz.targetsoftwaredevelopment.myapplication.data.remote.responses.LoginUserResponse
import uz.targetsoftwaredevelopment.myapplication.data.remote.responses.MainPageDataResponse
import uz.targetsoftwaredevelopment.myapplication.data.remote.responses.RegisterUserResponse
import uz.targetsoftwaredevelopment.myapplication.data.remote.responses.VideoData

interface BaseRepository {
    fun registerUser(data: RegisterUserRequest): Flow<Result<RegisterUserResponse?>>

    fun loginUser(data: LoginUserRequest): Flow<Result<LoginUserResponse?>>

    fun getMainPageData(): Flow<Result<MainPageDataResponse?>>

    fun getAllVideos(): Flow<Result<List<VideoData?>?>>
}