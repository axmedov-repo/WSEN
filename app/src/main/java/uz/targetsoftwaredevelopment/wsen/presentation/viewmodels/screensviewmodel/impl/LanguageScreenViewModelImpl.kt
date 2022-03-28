package uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.screensviewmodel.impl

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.targetsoftwaredevelopment.wsen.domain.repository.BaseRepository
import uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.screensviewmodel.LanguageScreenViewModel
import javax.inject.Inject

@HiltViewModel
class LanguageScreenViewModelImpl @Inject constructor(private val baseRepository: BaseRepository) :
    ViewModel(), LanguageScreenViewModel {

}