package uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.pagesvidemodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.targetsoftwaredevelopment.wsen.domain.repository.BaseRepository
import uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.pagesvidemodel.RegisterPageViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterPageViewModelImpl @Inject constructor(private val baseRepository: BaseRepository) :
    ViewModel(), RegisterPageViewModel {

    override val errorLiveData = MutableLiveData<List<String>>()

    init {
        baseRepository.setRegisterErrorListener { errorMessage ->
            errorLiveData.postValue(errorMessage)
        }
    }
}