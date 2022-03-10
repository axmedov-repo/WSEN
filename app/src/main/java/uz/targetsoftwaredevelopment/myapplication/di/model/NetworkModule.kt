package uz.targetsoftwaredevelopment.myapplication.di.model

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.targetsoftwaredevelopment.myapplication.data.remote.ApiClient
import uz.targetsoftwaredevelopment.myapplication.data.remote.api.MainPageApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun getMainPageApi(): MainPageApi = ApiClient.retrofit.create(MainPageApi::class.java)

}