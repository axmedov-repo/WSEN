package uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.screensviewmodel

import androidx.lifecycle.LiveData
import uz.targetsoftwaredevelopment.myapplication.data.remote.requests.LoginUserRequest
import uz.targetsoftwaredevelopment.myapplication.data.remote.requests.RegisterUserRequest
import uz.targetsoftwaredevelopment.myapplication.data.remote.responses.LoginUserResponse
import uz.targetsoftwaredevelopment.myapplication.data.remote.responses.RegisterUserResponse

interface AuthScreenViewModel {
    val registerUserResponseLiveData: LiveData<RegisterUserResponse>
    val loginUserResponseLiveData: LiveData<LoginUserResponse>
    val errorLiveData: LiveData<String>

    fun registerUser(data: RegisterUserRequest)
    fun loginUser(data: LoginUserRequest)
}