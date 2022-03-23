package uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.pagesvidemodel.impl

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.targetsoftwaredevelopment.myapplication.domain.repository.BaseRepository
import uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.pagesvidemodel.AddVideoPageViewModel
import javax.inject.Inject

@HiltViewModel
class AddVidePageViewModelImpl @Inject constructor(private val baseRepository: BaseRepository) :
    ViewModel(), AddVideoPageViewModel {

}