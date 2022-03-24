package uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.pagesvidemodel

import androidx.lifecycle.LiveData
import uz.targetsoftwaredevelopment.myapplication.data.remote.responses.VideoData

interface AllVideoPageViewModel {

    val getAllVideosLiveData:LiveData<List<VideoData?>?>

    fun getAllVideosPageData()
}