package uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.screensviewmodel

import androidx.lifecycle.LiveData

interface BasicScreenViewModel {
    val logoutUserResponseLiveData: LiveData<String>
    val errorLiveData: LiveData<String>

    fun logoutUser()
}