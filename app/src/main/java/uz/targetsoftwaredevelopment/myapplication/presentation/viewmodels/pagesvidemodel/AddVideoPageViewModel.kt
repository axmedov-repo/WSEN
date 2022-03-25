package uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.pagesvidemodel

import androidx.lifecycle.LiveData
import uz.targetsoftwaredevelopment.myapplication.data.remote.requests.AddVideoRequest
import uz.targetsoftwaredevelopment.myapplication.data.remote.responses.AddVideoResponse
import java.io.File

interface AddVideoPageViewModel {
    val addVideoResponseLiveData: LiveData<AddVideoResponse>
    val errorLiveData: LiveData<Unit>
    val videoCompressedLiceData: LiveData<File>

    fun addVideo(data: AddVideoRequest)
    fun videoCompressed(file : File)
}