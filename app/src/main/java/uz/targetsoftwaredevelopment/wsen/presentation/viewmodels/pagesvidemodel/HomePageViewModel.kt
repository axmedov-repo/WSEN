package uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.pagesvidemodel

import androidx.lifecycle.LiveData
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.CategoriesItem
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.Statistics

interface HomePageViewModel {
    val getMapImageLiveData: LiveData<String>
    val statisticsLiveData: LiveData<Statistics>
    val categoriesLiveData: LiveData<List<CategoriesItem?>?>

    fun getMapImageUrlString(imageUrl: String)

    fun getHomePageData()
}