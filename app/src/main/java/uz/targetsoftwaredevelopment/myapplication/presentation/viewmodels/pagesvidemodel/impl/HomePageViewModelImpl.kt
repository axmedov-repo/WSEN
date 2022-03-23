package uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.pagesvidemodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.targetsoftwaredevelopment.myapplication.data.remote.responses.CategoriesItem
import uz.targetsoftwaredevelopment.myapplication.data.remote.responses.Statistics
import uz.targetsoftwaredevelopment.myapplication.domain.repository.BaseRepository
import uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.pagesvidemodel.HomePageViewModel
import javax.inject.Inject

@HiltViewModel
class HomePageViewModelImpl @Inject constructor(private val baseRepository: BaseRepository) :
    HomePageViewModel, ViewModel() {
    override val getMapImageLiveData = MutableLiveData<String>()
    override val statisticsLiveData = MutableLiveData<Statistics>()
    override val categoriesLiveData = MutableLiveData<List<CategoriesItem?>?>()

    override fun getMapImageUrlString(imageUrl: String) {
        getMapImageLiveData.value = imageUrl
    }

    override fun getHomePageData() {
        baseRepository.getMainPageData().onEach { result ->
            result.onSuccess { mainPageData ->
                statisticsLiveData.value = mainPageData!!.statistics!!
                categoriesLiveData.value = mainPageData.categories!!
            }
            result.onFailure {

            }
        }.launchIn(viewModelScope)
    }
}