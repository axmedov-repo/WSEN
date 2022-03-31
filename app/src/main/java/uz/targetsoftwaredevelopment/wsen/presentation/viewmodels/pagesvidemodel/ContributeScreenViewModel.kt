package uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.pagesvidemodel

import androidx.lifecycle.LiveData
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.LikeVideResponseData
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.VideoData

interface ContributeScreenViewModel {
    val favouriteVideosLiveData: LiveData<List<VideoData?>?>
    val likeChangedLiveData: LiveData<LikeVideResponseData>
    val errorLiveData: LiveData<String>

    fun getFavouriteVideos()

    fun changeLike(videoData: VideoData)
}