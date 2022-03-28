package uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.pagesvidemodel

import androidx.lifecycle.LiveData
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.VideoData

interface AllVideoPageViewModel {
    val allVideosLiveData: LiveData<List<VideoData?>?>
    val errorLiveData: LiveData<Unit>

    fun getAllVideos()
}