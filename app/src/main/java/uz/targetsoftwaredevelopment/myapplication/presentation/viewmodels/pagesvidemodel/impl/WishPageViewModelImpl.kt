package uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.pagesvidemodel.impl

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.targetsoftwaredevelopment.myapplication.domain.repository.BaseRepository
import uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.pagesvidemodel.WishPageViewModel
import javax.inject.Inject

@HiltViewModel
class WishPageViewModelImpl @Inject constructor(private val baseRepository: BaseRepository) :
    ViewModel(), WishPageViewModel {

}