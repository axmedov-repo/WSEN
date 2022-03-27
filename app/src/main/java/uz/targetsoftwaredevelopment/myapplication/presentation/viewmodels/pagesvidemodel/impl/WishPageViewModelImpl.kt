package uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.pagesvidemodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.targetsoftwaredevelopment.myapplication.R
import uz.targetsoftwaredevelopment.myapplication.data.remote.responses.LikeVideResponseData
import uz.targetsoftwaredevelopment.myapplication.data.remote.responses.VideoData
import uz.targetsoftwaredevelopment.myapplication.domain.repository.BaseRepository
import uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.pagesvidemodel.WishPageViewModel
import uz.targetsoftwaredevelopment.myapplication.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class WishPageViewModelImpl @Inject constructor(private val baseRepository: BaseRepository) :
    ViewModel(), WishPageViewModel {

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