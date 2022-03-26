package uz.targetsoftwaredevelopment.myapplication.domain.repository.impl

import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.targetsoftwaredevelopment.myapplication.data.enums.SplashOpenScreenTypes
import uz.targetsoftwaredevelopment.myapplication.data.local.LocalStorage
import uz.targetsoftwaredevelopment.myapplication.data.remote.api.BaseApi
import uz.targetsoftwaredevelopment.myapplication.data.remote.requests.*
import uz.targetsoftwaredevelopment.myapplication.data.remote.responses.*
import uz.targetsoftwaredevelopment.myapplication.domain.repository.BaseRepository
import javax.inject.Inject

class BaseRepositoryImpl @Inject constructor(
    private val baseApi: BaseApi,
    private val localStorage: LocalStorage
) : BaseRepository {
    private val gson = Gson()

    private var registerErrorListener: ((String) -> Unit)? = null
    override fun setRegisterErrorListener(f: (String) -> Unit) {
        registerErrorListener = f
    }

    private var loginErrorListener: ((String) -> Unit)? = null
    override fun setLoginErrorListener(f: (String) -> Unit) {
        loginErrorListener = f
    }

    override fun getSplashOpenScreen(): SplashOpenScreenTypes {
        return if (localStorage.splashOpenScreen == SplashOpenScreenTypes.BASE_SCREEN.name) {
            SplashOpenScreenTypes.BASE_SCREEN
        } else {
            SplashOpenScreenTypes.AUTH_SCREEN
        }
    }

    override fun setSplashOpenScreen(type: SplashOpenScreenTypes) {
        localStorage.splashOpenScreen = type.name
    }

    override fun registerUser(data: RegisterUserRequest): Flow<Result<RegisterUserResponse?>> =
        flow {
            val response = baseApi.registerUser(data)
            if (response.isSuccessful) {
                emit(Result.success(response.body()))
            } else {
                var message = "Xatolik yuzaga keldi"
                response.errorBody()?.let {
                    message = gson.fromJson(
                        it.string(),
                        ErrorEmailResponse::class.java
                    ).email?.get(0).toString()
                }
                registerErrorListener?.invoke(message)
                emit(Result.failure(Throwable(message)))
            }
        }.flowOn(Dispatchers.IO)

    override fun loginUser(data: LoginUserRequest): Flow<Result<LoginUserResponse?>> =
        flow {
            val response = baseApi.loginUser(data)
            if (response.isSuccessful) {
                emit(Result.success(response.body()))
                if (response.body()!!.token != null) {
                    localStorage.token = "Token ${response.body()!!.token!!}"
                    localStorage.userId = "${response.body()!!.user?.id}"
                }
                setSplashOpenScreen(SplashOpenScreenTypes.BASE_SCREEN)
            } else {
                var message = "Xatolik yuzaga keldi"
                /*if (response.errorBody() != null) {
                    message = gson.fromJson(
                        response.errorBody()!!.string(),
                        ErrorEmailResponse::class.java
                    ).email?.first().toString()
                }*/
                loginErrorListener?.invoke(message)
                emit(Result.failure(Throwable(message)))
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

    override fun addVideo(data: AddVideoRequest): Flow<Result<AddVideoResponse?>> =
        flow {
            Log.d("ADDBTN", "repository da add videoga kirdi")
            val response = baseApi.addVideo(data)
            if (response.isSuccessful) {
                Log.d("ADDBTN", "repository da success")
                emit(Result.success(response.body()))
            }
        }.flowOn(Dispatchers.IO)

    override fun getUserData(): Flow<Result<UserData>> = flow {
        val response =
            baseApi.getUserData("http://147.182.250.241/uz/client/me/${localStorage.userId}/")
        if (response.isSuccessful) {
            emit(Result.success(response.body()!!))
        }
    }.flowOn(Dispatchers.IO)

    override fun getUserPhoneNumber(): String {
        return localStorage.userPhoneNumber
    }

    override fun setUserPhoneNumber(phoneNumber: String) {
        localStorage.userPhoneNumber = phoneNumber
    }

    override fun editUserData(userData: UserData): Flow<Result<UserData>> = flow {
        val response = baseApi.editUserData("uz/client/me/${localStorage.userId}/", userData)
        if (response.isSuccessful) {
            emit(Result.success(response.body()!!))
        }
    }.flowOn(Dispatchers.IO)

    override fun editMyVideo(videoData: EditVideoRequest): Flow<Result<EditVideoResponse>> = flow {
        val response = baseApi.editMyVideo("uz/api/my-post/${videoData.id}/", videoData)
        if (response.isSuccessful) {
            emit(Result.success(response.body()!!))
        }
    }.flowOn(Dispatchers.IO)
}