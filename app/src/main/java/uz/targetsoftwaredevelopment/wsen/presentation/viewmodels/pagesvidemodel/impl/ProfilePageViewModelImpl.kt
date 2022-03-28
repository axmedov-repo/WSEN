package uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.pagesvidemodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.targetsoftwaredevelopment.wsen.R
import uz.targetsoftwaredevelopment.wsen.data.remote.requests.UserData
import uz.targetsoftwaredevelopment.wsen.domain.repository.BaseRepository
import uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.pagesvidemodel.ProfilePageViewModel
import uz.targetsoftwaredevelopment.wsen.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class ProfilePageViewModelImpl @Inject constructor(private val baseRepository: BaseRepository) :
    ViewModel(), ProfilePageViewModel {

    override val getUserDataLiveData = MutableLiveData<UserData>()
    override val getUserPhoneNumberLiveData = MutableLiveData<String>()
    override val errorLiveData = MutableLiveData<String>()
    override val editUserDataLiveData = MutableLiveData<UserData>()

    override fun getUserData() {
        if (isConnected()) {
            baseRepository.getUserData().onEach {
                it.onSuccess {
                    getUserDataLiveData.value = it
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

    override fun editUserData(userData: UserData) {
        if (isConnected()) {
            baseRepository.editUserData(userData).onEach {
                it.onSuccess {
                    editUserDataLiveData.value = it
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
