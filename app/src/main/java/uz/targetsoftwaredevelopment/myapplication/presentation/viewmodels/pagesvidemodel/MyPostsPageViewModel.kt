package uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.pagesvidemodel

import androidx.lifecycle.LiveData
import uz.targetsoftwaredevelopment.myapplication.data.remote.responses.VideoData

interface MyPostsPageViewModel {
    val allMyVideosLiveData: LiveData<List<VideoData?>>
    val errorLiveData: LiveData<String>

    fun getAllMyVideos()
}