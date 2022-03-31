package uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.screensviewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.targetsoftwaredevelopment.wsen.R
import uz.targetsoftwaredevelopment.wsen.app.App
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.LikeVideResponseData
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.VideoData
import uz.targetsoftwaredevelopment.wsen.domain.repository.BaseRepository
import uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.screensviewmodel.WatchVideoScreenViewModel
import uz.targetsoftwaredevelopment.wsen.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class WatchVideoScreenViewModelImpl @Inject constructor(private val baseRepository: BaseRepository) :
    ViewModel(), WatchVideoScreenViewModel {

    override val changeLikeLiveData = MutableLiveData<LikeVideResponseData?>()
    override val errorLiveData = MutableLiveData<String>()

    override fun changeLikeVide(video : VideoData) {
        if(isConnected()){
            baseRepository.changeLike(video).onEach {
                it.onSuccess { likeVideResponse->
                    changeLikeLiveData.value = likeVideResponse.data
                }
                it.onFailure {
                    errorLiveData.value = App.instance.getString(R.string.some_error)
                }
            }.launchIn(viewModelScope)
        }else{
            errorLiveData.value = App.instance.getString(R.string.internet_disconnected)
        }
    }
}