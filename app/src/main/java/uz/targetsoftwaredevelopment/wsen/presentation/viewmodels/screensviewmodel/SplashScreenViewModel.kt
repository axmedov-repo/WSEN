package uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.screensviewmodel

import androidx.lifecycle.LiveData
import uz.targetsoftwaredevelopment.wsen.data.enums.SplashOpenScreenTypes

interface SplashScreenViewModel {
    val splashOpenScreenLiveData: LiveData<SplashOpenScreenTypes>

    fun getSplashOpenScreen()
}