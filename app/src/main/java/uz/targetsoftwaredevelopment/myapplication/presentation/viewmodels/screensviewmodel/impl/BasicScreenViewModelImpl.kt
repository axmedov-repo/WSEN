package uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.screensviewmodel.impl

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.targetsoftwaredevelopment.myapplication.domain.repository.BaseRepository
import uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.screensviewmodel.BasicScreenViewModel
import javax.inject.Inject

@HiltViewModel
class BasicScreenViewModelImpl @Inject constructor(private val baseRepository: BaseRepository) :
    ViewModel(), BasicScreenViewModel {

}