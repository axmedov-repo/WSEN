package uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.screensviewmodel

import androidx.lifecycle.LiveData
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.LikeVideResponseData
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.VideoData

interface WatchVideoScreenViewModel {
    val changeLikeLiveData:LiveData<LikeVideResponseData?>
    val errorLiveData:LiveData<String>

    fun changeLikeVide(video:VideoData)


}