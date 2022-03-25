package uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.screensviewmodel.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.targetsoftwaredevelopment.myapplication.data.enums.SplashOpenScreenTypes
import uz.targetsoftwaredevelopment.myapplication.domain.repository.BaseRepository
import uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.screensviewmodel.SplashScreenViewModel
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModelImpl @Inject constructor(private val baseRepository: BaseRepository) :
    ViewModel(), SplashScreenViewModel {

    //    override val splashOpenScreenLiveData : LiveData<SplashOpenScreenTypes>
    override val splashOpenScreenLiveData = MutableLiveData<SplashOpenScreenTypes>()


    override fun getSplashOpenScreen() {
//        splashOpenScreenLiveData.value = baseRepository.ge
    }
}