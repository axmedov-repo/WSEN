package uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.screensviewmodel

import androidx.lifecycle.LiveData
import uz.targetsoftwaredevelopment.wsen.data.remote.requests.UserDataRequest
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.LogoutResponse
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.UserDataResponse

interface BasicScreenViewModel {
    val logoutUserResponseLiveData: LiveData<LogoutResponse>
    val userDataLiveDataRequest: LiveData<UserDataResponse>
    val errorLiveData: LiveData<String>

    fun logoutUser()
    fun getUserData()
}