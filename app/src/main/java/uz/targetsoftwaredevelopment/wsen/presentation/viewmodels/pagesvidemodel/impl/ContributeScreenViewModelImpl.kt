package uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.pagesvidemodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.targetsoftwaredevelopment.wsen.R
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.LikeVideResponseData
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.VideoData
import uz.targetsoftwaredevelopment.wsen.domain.repository.BaseRepository
import uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.pagesvidemodel.ContributeScreenViewModel
import uz.targetsoftwaredevelopment.wsen.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class ContributeScreenViewModelImpl @Inject constructor(private val baseRepository: BaseRepository) :
    ViewModel(), ContributeScreenViewModel {

    override val favouriteVideosLiveData = MutableLiveData<List<VideoData?>?>()
    override val likeChangedLiveData = MutableLiveData<LikeVideResponseData>()
    override val errorLiveData = MutableLiveData<String>()

    override fun getFavouriteVideos() {
        if (isConnected()) {
            baseRepository.getAllFavouriteVideos().onEach {
                it.onSuccess {
                    favouriteVideosLiveData.value = it.results?.data
                }
                it.onFailure {
                    errorLiveData.value = it.message
                }
            }.launchIn(viewModelScope)
        } else {
            errorLiveData.value = "${R.string.internet_disconnected}"
        }
    }

    override fun changeLike(videoData: VideoData) {
        if (isConnected()) {
            baseRepository.changeLike(videoData).onEach {
                it.onSuccess {
                    likeChangedLiveData.value = it.data!!
                }
                it.onFailure {
                    errorLiveData.value = it.message
                }
            }.launchIn(viewModelScope)
        } else {
            errorLiveData.value = "${R.string.internet_disconnected}"
        }
    }
}