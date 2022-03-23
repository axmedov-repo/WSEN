package uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.pagesvidemodel.impl

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.targetsoftwaredevelopment.myapplication.domain.repository.BaseRepository
import uz.targetsoftwaredevelopment.myapplication.presentation.viewmodels.pagesvidemodel.SignUpPageViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpPageViewModelImpl @Inject constructor(private val baseRepository: BaseRepository) :
    ViewModel(), SignUpPageViewModel {

}