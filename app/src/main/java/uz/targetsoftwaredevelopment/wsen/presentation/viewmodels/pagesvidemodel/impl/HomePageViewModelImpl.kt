package uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.pagesvidemodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.targetsoftwaredevelopment.wsen.R
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.CategoriesItem
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.Statistics
import uz.targetsoftwaredevelopment.wsen.domain.repository.BaseRepository
import uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.pagesvidemodel.HomePageViewModel
import uz.targetsoftwaredevelopment.wsen.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class HomePageViewModelImpl @Inject constructor(private val baseRepository: BaseRepository) :
    HomePageViewModel, ViewModel() {
    override val getMapImageLiveData = MutableLiveData<String>()
    override val statisticsLiveData = MutableLiveData<Statistics>()
    override val categoriesLiveData = MutableLiveData<List<CategoriesItem?>?>()
    override val errorLiveData = MutableLiveData<String>()

    override fun getMapImageUrlString(imageUrl: String) {
        getMapImageLiveData.value = imageUrl
    }

    override fun getHomePageData() {
        if (isConnected()) {
            baseRepository.getMainPageData().onEach { result ->
                result.onSuccess { mainPageData ->
                    statisticsLiveData.value = mainPageData!!.statistics!!
                    categoriesLiveData.value = mainPageData.categories!!
                }
                result.onFailure {
                    errorLiveData.value = it.message
                }
            }.launchIn(viewModelScope)
        } else {
            errorLiveData.value = "${R.string.internet_disconnected}"
        }
    }
}