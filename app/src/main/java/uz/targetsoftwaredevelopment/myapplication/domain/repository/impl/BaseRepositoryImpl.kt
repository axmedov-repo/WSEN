package uz.targetsoftwaredevelopment.myapplication.domain.repository.impl

import uz.targetsoftwaredevelopment.myapplication.data.local.LocalStorage
import uz.targetsoftwaredevelopment.myapplication.domain.repository.BaseRepository
import javax.inject.Inject

class BaseRepositoryImpl @Inject constructor(private val localStorage: LocalStorage) :
    BaseRepository {


}