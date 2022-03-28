package uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.pagesvidemodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.targetsoftwaredevelopment.wsen.domain.repository.BaseRepository
import uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.pagesvidemodel.LoginPageViewModel
import javax.inject.Inject

@HiltViewModel
class LoginPageViewModelImpl @Inject constructor(private val baseRepository: BaseRepository) :
    ViewModel(), LoginPageViewModel {

    override val errorLiveData = MutableLiveData<String>()

    init {
        baseRepository.setLoginErrorListener { errorMessage ->
            errorLiveData.postValue(errorMessage)
        }
    }
}