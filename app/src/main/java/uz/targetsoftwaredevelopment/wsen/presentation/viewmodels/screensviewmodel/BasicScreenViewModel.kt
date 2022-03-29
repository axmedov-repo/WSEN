package uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.screensviewmodel

import androidx.lifecycle.LiveData
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.LogoutResponse

interface BasicScreenViewModel {
    val logoutUserResponseLiveData: LiveData<LogoutResponse>
    val errorLiveData: LiveData<String>

    fun logoutUser()
}