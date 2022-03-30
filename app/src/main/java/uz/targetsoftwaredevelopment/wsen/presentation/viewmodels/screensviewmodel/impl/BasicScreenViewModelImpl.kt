package uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.screensviewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.targetsoftwaredevelopment.wsen.R
import uz.targetsoftwaredevelopment.wsen.data.remote.requests.UserDataRequest
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.LogoutResponse
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.UserDataResponse
import uz.targetsoftwaredevelopment.wsen.domain.repository.BaseRepository
import uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.screensviewmodel.BasicScreenViewModel
import uz.targetsoftwaredevelopment.wsen.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class BasicScreenViewModelImpl @Inject constructor(private val baseRepository: BaseRepository) :
    ViewModel(), BasicScreenViewModel {
    override val logoutUserResponseLiveData = MutableLiveData<LogoutResponse>()
    override val errorLiveData = MutableLiveData<String>()
    override val userDataLiveDataRequest = MutableLiveData<UserDataResponse>()

    override fun logoutUser() {
        if (isConnected()) {
            baseRepository.logoutUser().onEach {
                it.onSuccess {
                    logoutUserResponseLiveData.value = it
                }
                it.onFailure {
                    errorLiveData.value = it.message
                }
            }.launchIn(viewModelScope)
        } else {
            errorLiveData.value = "${R.string.internet_disconnected}"
        }
    }

    override fun getUserData() {
        if (isConnected()) {
            baseRepository.getUserData().onEach {
                it.onSuccess {
                    userDataLiveDataRequest.value = it
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