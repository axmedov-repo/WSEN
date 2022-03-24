package uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.screensviewmodel

import androidx.lifecycle.LiveData
import uz.targetsoftwaredevelopment.myapplication.data.enums.SplashOpenScreenTypes

interface SplashScreenViewModel {
    val splashOpenScreenLiveData: LiveData<SplashOpenScreenTypes>

    fun getSplashOpenScreen()
}