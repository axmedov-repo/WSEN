package uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.screensviewmodel.impl

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.targetsoftwaredevelopment.wsen.data.remote.requests.LoginUserRequest
import uz.targetsoftwaredevelopment.wsen.data.remote.requests.RegisterUserRequest
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.LoginUserResponse
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.RegisterUserResponse
import uz.targetsoftwaredevelopment.wsen.domain.repository.BaseRepository
import uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.screensviewmodel.AuthScreenViewModel
import uz.targetsoftwaredevelopment.wsen.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class AuthScreenViewModelImpl @Inject constructor(private val baseRepository: BaseRepository) :
    ViewModel(), AuthScreenViewModel {
    override val registerUserResponseLiveData = MutableLiveData<RegisterUserResponse>()
    override val loginUserResponseLiveData = MutableLiveData<LoginUserResponse>()
    override val errorLiveData = MutableLiveData<String>()

    override fun registerUser(data: RegisterUserRequest) {
        if (isConnected()) {
            baseRepository.registerUser(data).onEach {
                it.onSuccess {
                    registerUserResponseLiveData.value = it
                }
                it.onFailure {
                    errorLiveData.value = it.message
                }
            }.launchIn(viewModelScope)
        } else {
            errorLiveData.value = "Internet ga ulanmagan!"
        }
    }

    override fun loginUser(data: LoginUserRequest) {
        if (isConnected()) {
            baseRepository.loginUser(data).onEach {
                it.onSuccess {
                    Log.d("LOGINCHECK", "Login viewmodel success")
                    loginUserResponseLiveData.value = it
                }
                it.onFailure {
                    errorLiveData.value = it.message
                }
            }.launchIn(viewModelScope)
        } else {
            errorLiveData.value = "Internet ga ulanmagan!"
        }
    }
}