package uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.screensviewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.targetsoftwaredevelopment.myapplication.data.remote.requests.LoginUserRequest
import uz.targetsoftwaredevelopment.myapplication.data.remote.requests.RegisterUserRequest
import uz.targetsoftwaredevelopment.myapplication.data.remote.responses.LoginUserResponse
import uz.targetsoftwaredevelopment.myapplication.data.remote.responses.RegisterUserResponse
import uz.targetsoftwaredevelopment.myapplication.domain.repository.BaseRepository
import uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.screensviewmodel.AuthScreenViewModel
import uz.targetsoftwaredevelopment.myapplication.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class AuthScreenViewModelImpl @Inject constructor(private val baseRepository: BaseRepository) :
    ViewModel(), AuthScreenViewModel {
    override val registerUserResponseLiveData = MutableLiveData<RegisterUserResponse>()
    override val loginUserResponseLiveData = MutableLiveData<LoginUserResponse>()
    override val errorLiveData = MutableLiveData<Unit>()

    override fun registerUser(data: RegisterUserRequest) {
        if (isConnected()) {
            baseRepository.registerUser(data).onEach {
                it.onSuccess {
                    registerUserResponseLiveData.value = it
                }
                it.onFailure {
                    errorLiveData.value = Unit
                }
            }.launchIn(viewModelScope)
        } else {
            errorLiveData.value = Unit
        }
    }

    override fun loginUser(data: LoginUserRequest) {
        if (isConnected()) {
            baseRepository.loginUser(data).onEach {
                it.onSuccess {
                    loginUserResponseLiveData.value = it
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