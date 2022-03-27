package uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.pagesvidemodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.targetsoftwaredevelopment.myapplication.R
import uz.targetsoftwaredevelopment.myapplication.data.remote.responses.VideoData
import uz.targetsoftwaredevelopment.myapplication.domain.repository.BaseRepository
import uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.pagesvidemodel.MyPostsPageViewModel
import uz.targetsoftwaredevelopment.myapplication.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class MyPostsPageViewModelImpl @Inject constructor(private val baseRepository: BaseRepository) :
    ViewModel(), MyPostsPageViewModel {

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
}