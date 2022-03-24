package uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.pagesvidemodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.targetsoftwaredevelopment.myapplication.data.remote.responses.VideoData
import uz.targetsoftwaredevelopment.myapplication.domain.repository.pagesrepository.impl.ALlVideosBaseRepository
import uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.pagesvidemodel.AllVideoPageViewModel
import javax.inject.Inject

@HiltViewModel
class AllVideoPageViewModelImpl @Inject constructor(private val aLlVideosBaseRepository : ALlVideosBaseRepository) :
    ViewModel(), AllVideoPageViewModel {

    override val getAllVideosLiveData = MutableLiveData<List<VideoData?>?>()


    override fun getAllVideosPageData() {
        aLlVideosBaseRepository.getAllVideosData().onEach {result->
            result.onSuccess { allVideosData ->
                getAllVideosLiveData.value = allVideosData!!.results
            }
            result.onFailure {

            }
        }.launchIn(viewModelScope)
    }
}