package uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels

import androidx.lifecycle.LiveData

interface HomeScreenViewModel {
    val getMapImageLiveData: LiveData<String>

    fun getMapImageUrlString(imageUrl: String)
}