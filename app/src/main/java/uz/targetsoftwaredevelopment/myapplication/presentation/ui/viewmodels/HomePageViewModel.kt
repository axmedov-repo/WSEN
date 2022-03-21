package uz.targetsoftwaredevelopment.myapplication.presentation.ui.viewmodels

import androidx.lifecycle.LiveData
import uz.targetsoftwaredevelopment.myapplication.data.remote.responses.CategoriesItem
import uz.targetsoftwaredevelopment.myapplication.data.remote.responses.Statistics

interface HomePageViewModel {
    val getMapImageLiveData: LiveData<String>
    val statisticsLiveData: LiveData<Statistics>
    val categoriesLiveData: LiveData<List<CategoriesItem?>?>

    fun getMapImageUrlString(imageUrl: String)

    fun getHomePageData()
}