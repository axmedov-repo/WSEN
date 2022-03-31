package uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.pagesvidemodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.targetsoftwaredevelopment.wsen.data.remote.requests.AddVideoRequest
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.AddVideoResponse
import uz.targetsoftwaredevelopment.wsen.domain.repository.BaseRepository
import uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.pagesvidemodel.AddVideoPageViewModel
import uz.targetsoftwaredevelopment.wsen.utils.isConnected
import java.io.File
import javax.inject.Inject

@HiltViewModel
class AddVidePageViewModelImpl @Inject constructor(private val baseRepository: BaseRepository) :
    ViewModel(), AddVideoPageViewModel {

    override val addVideoResponseLiveData = MutableLiveData<AddVideoResponse>()
    override val errorLiveData = MutableLiveData<Unit>()
    override val videoCompressedLiveData = MutableLiveData<File>()


    override fun videoCompressed(file: File) {
        videoCompressedLiveData.value = file
    }

    override fun addVideo(data: AddVideoRequest) {
        if (isConnected()) {
            baseRepository.addVideo(data).onEach {
                it.onSuccess {
                    addVideoResponseLiveData.value = it
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
