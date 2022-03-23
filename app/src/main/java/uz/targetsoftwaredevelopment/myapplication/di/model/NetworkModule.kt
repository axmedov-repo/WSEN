package uz.targetsoftwaredevelopment.myapplication.di.model

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.targetsoftwaredevelopment.myapplication.data.remote.ApiClient
import uz.targetsoftwaredevelopment.myapplication.data.remote.api.BaseApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun getBaseApi(): BaseApi = ApiClient.retrofit.create(BaseApi::class.java)

}