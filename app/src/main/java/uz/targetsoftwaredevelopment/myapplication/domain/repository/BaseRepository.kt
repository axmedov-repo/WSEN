package uz.targetsoftwaredevelopment.myapplication.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.targetsoftwaredevelopment.myapplication.data.remote.responses.MainPageDataResponse

interface BaseRepository {
    fun getMainPageData(): Flow<Result<MainPageDataResponse?>>
}