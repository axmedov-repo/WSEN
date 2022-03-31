package uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.screensviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.targetsoftwaredevelopment.wsen.data.remote.requests.EditVideoRequest
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.EditVideoResponse

interface EditVideoScreenViewModel {
    val editVideoLiveData : LiveData<EditVideoResponse>
     val errorLiveData : LiveData<String>
    fun editVideo(editVideoRequest: EditVideoRequest)
}