package uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.pagesvidemodel

import androidx.lifecycle.LiveData
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.LikeVideResponseData
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.VideoData

interface AllVideoPageViewModel {
    val allVideosLiveData: LiveData<List<VideoData?>?>
    val changeLikeLiveData:LiveData<LikeVideResponseData?>
    val errorLiveData: LiveData<String>

    fun getAllVideos()

    fun changeLikeVideo(videoData : VideoData)
}