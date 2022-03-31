package uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.pagesvidemodel

import androidx.lifecycle.LiveData
import uz.targetsoftwaredevelopment.wsen.data.remote.requests.SpamVideoRequest
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.LikeVideResponseData
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.SpamVideoResponse
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.VideoData

interface AllVideoPageViewModel {
    val allVideosLiveData: LiveData<List<VideoData?>?>
    val errorLiveData: LiveData<String>
    val changeLikeLiveData:LiveData<LikeVideResponseData?>
    val spamVideoResponseLiveData: LiveData<SpamVideoResponse>

    fun getAllVideos()

    fun changeLikeVideo(videoData : VideoData)

    fun spamVideo(spamVideoRequest: SpamVideoRequest)
}