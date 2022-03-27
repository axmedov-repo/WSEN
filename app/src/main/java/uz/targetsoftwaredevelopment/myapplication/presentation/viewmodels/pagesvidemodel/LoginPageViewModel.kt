package uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.pagesvidemodel

import androidx.lifecycle.LiveData

interface LoginPageViewModel {
    val errorLiveData: LiveData<String>
}