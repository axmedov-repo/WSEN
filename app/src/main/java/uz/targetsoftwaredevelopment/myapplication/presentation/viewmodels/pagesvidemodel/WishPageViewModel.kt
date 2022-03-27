package uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.pagesvidemodel

import androidx.lifecycle.LiveData
import uz.targetsoftwaredevelopment.myapplication.data.remote.responses.LikeVideResponseData
import uz.targetsoftwaredevelopment.myapplication.data.remote.responses.VideoData

interface WishPageViewModel {
    val favouriteVideosLiveData: LiveData<List<VideoData?>?>
    val likeChangedLiveData: LiveData<LikeVideResponseData>
    val errorLiveData: LiveData<String>

    fun getFavouriteVideos()

    fun changeLike(videoData: VideoData)
}