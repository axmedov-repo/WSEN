package uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.screensviewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.targetsoftwaredevelopment.wsen.data.enums.SplashOpenScreenTypes
import uz.targetsoftwaredevelopment.wsen.domain.repository.BaseRepository
import uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.screensviewmodel.SplashScreenViewModel
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModelImpl @Inject constructor(private val baseRepository: BaseRepository) :
    ViewModel(), SplashScreenViewModel {
    override val splashOpenScreenLiveData = MutableLiveData<SplashOpenScreenTypes>()

    override fun getSplashOpenScreen() {
        splashOpenScreenLiveData.value = baseRepository.getSplashOpenScreen()
    }
}