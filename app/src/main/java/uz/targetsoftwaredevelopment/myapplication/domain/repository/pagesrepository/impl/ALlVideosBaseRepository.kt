package uz.targetsoftwaredevelopment.myapplication.domain.repository.pagesrepository.impl

import kotlinx.coroutines.flow.Flow
import uz.targetsoftwaredevelopment.myapplication.data.remote.responses.AllVideosResponse

interface ALlVideosBaseRepository {
    fun getAllVideosData(): Flow<Result<AllVideosResponse?>>
}