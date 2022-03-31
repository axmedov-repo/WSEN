package uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.screensviewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.targetsoftwaredevelopment.wsen.R
import uz.targetsoftwaredevelopment.wsen.data.remote.requests.EditVideoRequest
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.EditVideoResponse
import uz.targetsoftwaredevelopment.wsen.domain.repository.BaseRepository
import uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.screensviewmodel.EditVideoScreenViewModel
import uz.targetsoftwaredevelopment.wsen.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class EditVideoScreenViewModelImpl @Inject constructor(private val baseRepository: BaseRepository) :
    ViewModel(), EditVideoScreenViewModel {
    override val editVideoLiveData = MutableLiveData<EditVideoResponse>()
    override val errorLiveData = MutableLiveData<String>()

    override fun editVideo(editVideoRequest: EditVideoRequest) {
        if (isConnected()) {
            baseRepository.editMyVideo(editVideoRequest).onEach {
                it.onSuccess {
                    editVideoLiveData.value = it
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