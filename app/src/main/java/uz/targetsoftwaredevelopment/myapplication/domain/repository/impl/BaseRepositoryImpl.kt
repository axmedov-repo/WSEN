package uz.targetsoftwaredevelopment.myapplication.domain.repository.impl

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.targetsoftwaredevelopment.myapplication.data.local.LocalStorage
import uz.targetsoftwaredevelopment.myapplication.data.remote.api.BaseApi
import uz.targetsoftwaredevelopment.myapplication.data.remote.requests.LoginUserRequest
import uz.targetsoftwaredevelopment.myapplication.data.remote.requests.RegisterUserRequest
import uz.targetsoftwaredevelopment.myapplication.data.remote.responses.LoginUserResponse
import uz.targetsoftwaredevelopment.myapplication.data.remote.responses.MainPageDataResponse
import uz.targetsoftwaredevelopment.myapplication.data.remote.responses.RegisterUserResponse
import uz.targetsoftwaredevelopment.myapplication.data.remote.responses.VideoData
import uz.targetsoftwaredevelopment.myapplication.domain.repository.BaseRepository
import javax.inject.Inject

class BaseRepositoryImpl @Inject constructor(
    private val baseApi: BaseApi,
    private val localStorage: LocalStorage
) : BaseRepository {
    private val gson = Gson()

    override fun registerUser(data: RegisterUserRequest): Flow<Result<RegisterUserResponse?>> =
        flow {
            val response = baseApi.registerUser(data)
            if (response.isSuccessful) {
                emit(Result.success(response.body()))
            }
        }.flowOn(Dispatchers.IO)

    override fun loginUser(data: LoginUserRequest): Flow<Result<LoginUserResponse?>> =
        flow {
            val response = baseApi.loginUser(data)
            if (response.isSuccessful) {
                emit(Result.success(response.body()))
                localStorage.token = response.body()!!.token!!
            }
        }.flowOn(Dispatchers.IO)

    override fun getMainPageData(): Flow<Result<MainPageDataResponse?>> =
        flow {
            val response = baseApi.getMainPageData()
            if (response.isSuccessful) {
                emit(Result.success(response.body()))
            }
        }.flowOn(Dispatchers.IO)

    override fun getAllVideos(): Flow<Result<List<VideoData?>?>> =
        flow {
            val response = baseApi.getAllVideos()
            if (response.isSuccessful) {
                emit(Result.success(response.body()?.results))
            }
        }.flowOn(Dispatchers.IO)
}