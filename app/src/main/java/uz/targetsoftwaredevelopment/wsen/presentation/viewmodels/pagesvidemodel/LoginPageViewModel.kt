package uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.pagesvidemodel

import androidx.lifecycle.LiveData

interface LoginPageViewModel {
    val errorLiveData: LiveData<String>
}