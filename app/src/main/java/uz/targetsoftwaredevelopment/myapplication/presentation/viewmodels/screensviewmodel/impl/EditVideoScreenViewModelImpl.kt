package uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.screensviewmodel.impl

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.targetsoftwaredevelopment.myapplication.domain.repository.BaseRepository
import uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.screensviewmodel.EditVideoScreenViewModel
import javax.inject.Inject

@HiltViewModel
class EditVideoScreenViewModelImpl @Inject constructor(private val baseRepository: BaseRepository) :
    ViewModel(), EditVideoScreenViewModel {

}