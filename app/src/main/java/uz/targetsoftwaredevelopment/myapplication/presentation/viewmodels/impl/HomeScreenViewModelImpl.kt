package uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.targetsoftwaredevelopment.myapplication.domain.repository.BaseRepository
import uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.HomeScreenViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModelImpl @Inject constructor(private val baseRepository: BaseRepository) :
    HomeScreenViewModel, ViewModel() {
    override val getMapImageLiveData = MutableLiveData<String>()

    override fun getMapImageUrlString(imageUrl: String) {
        getMapImageLiveData.value = imageUrl
    }
}