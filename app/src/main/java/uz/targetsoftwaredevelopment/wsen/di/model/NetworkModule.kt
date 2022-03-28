package uz.targetsoftwaredevelopment.wsen.di.model

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.targetsoftwaredevelopment.wsen.data.remote.ApiClient
import uz.targetsoftwaredevelopment.wsen.data.remote.api.BaseApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun getBaseApi(): BaseApi = ApiClient.retrofit.create(BaseApi::class.java)

}