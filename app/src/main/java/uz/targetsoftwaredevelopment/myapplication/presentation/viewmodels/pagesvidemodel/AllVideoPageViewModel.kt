package uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.pagesvidemodel

import androidx.lifecycle.LiveData
import uz.targetsoftwaredevelopment.myapplication.data.remote.responses.VideoData

interface AllVideoPageViewModel {
    val allVideosLiveData: LiveData<List<VideoData?>?>
    val errorLiveData: LiveData<Unit>

    fun getAllVideos()
}