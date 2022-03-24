package uz.targetsoftwaredevelopment.myapplication.domain.repository.pagesrepository.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.targetsoftwaredevelopment.myapplication.data.remote.api.BaseApi
import uz.targetsoftwaredevelopment.myapplication.data.remote.responses.AllVideosResponse
import javax.inject.Inject

class AllVideosBaseRepositoryImpl @Inject constructor(
    private val baseApi:BaseApi,
):ALlVideosBaseRepository {

    override fun getAllVideosData() : Flow<Result<AllVideosResponse?>> = flow {
        val response = baseApi.getAllPostsData()
        if(response.isSuccessful){
            emit(Result.success(response.body()))
        }
    }.flowOn(Dispatchers.IO)
}