package uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.pagesvidemodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.targetsoftwaredevelopment.wsen.R
import uz.targetsoftwaredevelopment.wsen.data.remote.requests.EditVideoRequest
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.VideoData
import uz.targetsoftwaredevelopment.wsen.domain.repository.BaseRepository
import uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.pagesvidemodel.MyPostsPageViewModel
import uz.targetsoftwaredevelopment.wsen.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class MyPostsPageViewModelImpl @Inject constructor(private val baseRepository: BaseRepository) :
    ViewModel(), MyPostsPageViewModel {

    override val videoDeletedLiveData = MutableLiveData<Unit>()

    override val allMyVideosLiveData = MutableLiveData<List<VideoData?>>()
    override val errorLiveData = MutableLiveData<String>()

    override fun getAllMyVideos() {
        if (isConnected()) {
            baseRepository.getAllMyVideos().onEach {
                it.onSuccess {
                    allMyVideosLiveData.value = it
                }
                it.onFailure {
                    errorLiveData.value = it.message
                }
            }.launchIn(viewModelScope)
        } else {
            errorLiveData.value = "${R.string.internet_disconnected}"
        }
    }

    override fun deleteMyVideo(videoData: VideoData) {
        if (isConnected()) {
            baseRepository.deleteMyVideo(videoData).onEach {
                it.onSuccess {
                    videoDeletedLiveData.value = Unit
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