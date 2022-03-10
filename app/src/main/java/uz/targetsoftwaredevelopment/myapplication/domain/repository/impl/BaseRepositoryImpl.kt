package uz.targetsoftwaredevelopment.myapplication.domain.repository.impl

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.targetsoftwaredevelopment.myapplication.data.local.LocalStorage
import uz.targetsoftwaredevelopment.myapplication.data.remote.api.MainPageApi
import uz.targetsoftwaredevelopment.myapplication.data.remote.responses.MainPageDataResponse
import uz.targetsoftwaredevelopment.myapplication.domain.repository.BaseRepository
import javax.inject.Inject

class BaseRepositoryImpl @Inject constructor(
    private val mainPageApi: MainPageApi,
    private val localStorage: LocalStorage
) : BaseRepository {
    private val gson = Gson()

    override fun getMainPageData(): Flow<Result<MainPageDataResponse?>> = flow {
        val response = mainPageApi.getMainPageData()
        if (response.isSuccessful) {
            emit(Result.success(response.body()))
        }
    }.flowOn(Dispatchers.IO)
}