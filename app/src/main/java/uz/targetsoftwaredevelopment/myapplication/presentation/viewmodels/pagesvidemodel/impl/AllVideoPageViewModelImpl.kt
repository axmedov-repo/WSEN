package uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.pagesvidemodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.targetsoftwaredevelopment.myapplication.data.remote.responses.VideoData
import uz.targetsoftwaredevelopment.myapplication.domain.repository.BaseRepository
import uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.pagesvidemodel.AllVideoPageViewModel
import uz.targetsoftwaredevelopment.myapplication.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class AllVideoPageViewModelImpl @Inject constructor(private val baseRepository: BaseRepository) :
    ViewModel(), AllVideoPageViewModel {
    override val allVideosLiveData = MutableLiveData<List<VideoData?>?>()
    override val errorLiveData = MutableLiveData<Unit>()

    override fun getAllVideos() {
        if (isConnected()) {
            baseRepository.getAllVideos().onEach {
                it.onSuccess {
                    allVideosLiveData.value = it
                }
                it.onFailure {
                    errorLiveData.value = Unit
                }
            }.launchIn(viewModelScope)
        } else {
            errorLiveData.value = Unit
        }
    }
}