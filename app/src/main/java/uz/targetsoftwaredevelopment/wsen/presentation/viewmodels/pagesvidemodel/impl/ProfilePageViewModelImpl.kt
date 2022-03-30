package uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.pagesvidemodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.targetsoftwaredevelopment.wsen.R
import uz.targetsoftwaredevelopment.wsen.data.remote.requests.UserDataRequest
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.UserDataResponse
import uz.targetsoftwaredevelopment.wsen.domain.repository.BaseRepository
import uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.pagesvidemodel.ProfilePageViewModel
import uz.targetsoftwaredevelopment.wsen.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class ProfilePageViewModelImpl @Inject constructor(private val baseRepository: BaseRepository) :
    ViewModel(), ProfilePageViewModel {

    override val getUserDataLiveDataRequest = MutableLiveData<UserDataResponse>()
    override val getUserPhoneNumberLiveData = MutableLiveData<String>()
    override val errorLiveData = MutableLiveData<String>()
    override val editUserDataLiveDataRequest = MutableLiveData<UserDataResponse>()

    override fun getUserData() {
        if (isConnected()) {
            baseRepository.getUserData().onEach {
                it.onSuccess {
                    getUserDataLiveDataRequest.value = it
                }
                it.onFailure {
                    errorLiveData.value = it.message
                }
            }.launchIn(viewModelScope)
        } else {
            errorLiveData.value = "${R.string.internet_disconnected}"
        }
    }

    override fun getUserPhoneNumber() {
        getUserPhoneNumberLiveData.value = baseRepository.getUserPhoneNumber()
    }

    override fun setUserPhoneNumber(phoneNumber: String) {
        baseRepository.setUserPhoneNumber(phoneNumber)
    }

    override fun editUserData(userDataRequest: UserDataRequest) {
        if (isConnected()) {
            baseRepository.editUserData(userDataRequest).onEach {
                it.onSuccess {
                    editUserDataLiveDataRequest.value = it
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
