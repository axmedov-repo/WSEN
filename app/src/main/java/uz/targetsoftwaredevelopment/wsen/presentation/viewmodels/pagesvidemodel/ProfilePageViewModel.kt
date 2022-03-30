package uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.pagesvidemodel

import androidx.lifecycle.LiveData
import uz.targetsoftwaredevelopment.wsen.data.remote.requests.UserDataRequest
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.UserDataResponse

interface ProfilePageViewModel {
    val getUserDataLiveDataRequest: LiveData<UserDataResponse>
    val errorLiveData: LiveData<String>
    val getUserPhoneNumberLiveData: LiveData<String>
    val editUserDataLiveDataRequest: LiveData<UserDataResponse>

    fun getUserData()

    fun getUserPhoneNumber()

    fun setUserPhoneNumber(phoneNumber: String)

    fun editUserData(userDataRequest: UserDataRequest)
}