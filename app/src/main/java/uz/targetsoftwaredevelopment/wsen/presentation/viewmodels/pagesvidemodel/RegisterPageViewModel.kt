package uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.pagesvidemodel

import androidx.lifecycle.LiveData

interface RegisterPageViewModel {
    val errorLiveData: LiveData<List<String>>
}