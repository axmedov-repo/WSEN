package uz.targetsoftwaredevelopment.wsen.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.targetsoftwaredevelopment.wsen.data.enums.SplashOpenScreenTypes
import uz.targetsoftwaredevelopment.wsen.data.remote.requests.*
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.*

interface BaseRepository {

    fun getSplashOpenScreen(): SplashOpenScreenTypes

    fun setSplashOpenScreen(type: SplashOpenScreenTypes)

    fun registerUser(data: RegisterUserRequest): Flow<Result<RegisterUserResponse?>>

    fun loginUser(data: LoginUserRequest): Flow<Result<LoginUserResponse?>>

    fun logoutUser(): Flow<Result<LogoutResponse>>

    fun getMainPageData(): Flow<Result<MainPageDataResponse?>>

    fun getAllVideos(): Flow<Result<List<VideoData?>?>>

    fun addVideo(data: AddVideoRequest): Flow<Result<AddVideoResponse?>>

    fun setRegisterErrorListener(f: (List<String>) -> Unit)

    fun setLoginErrorListener(f: (String) -> Unit)

    fun getUserData(): Flow<Result<UserDataResponse>>

    fun getUserPhoneNumber(): String

    fun setUserPhoneNumber(phoneNumber: String)

    fun editUserData(userDataRequest: UserDataRequest): Flow<Result<UserDataResponse>>

    fun getAllMyVideos(): Flow<Result<List<VideoData?>>>

    fun editMyVideo(videoData: EditVideoRequest): Flow<Result<EditVideoResponse>>

    fun deleteMyVideo(videoData: VideoData): Flow<Result<String>>

    fun getAllFavouriteVideos(): Flow<Result<AllFavouriteVideosResponse>>

    fun changeLike(videoData: VideoData): Flow<Result<LikeVideoResponse>>
}
