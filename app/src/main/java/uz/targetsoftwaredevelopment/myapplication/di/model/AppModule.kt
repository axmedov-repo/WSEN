package uz.targetsoftwaredevelopment.myapplication.di.model

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.targetsoftwaredevelopment.myapplication.data.local.LocalStorage
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
/*
    @Provides
    @Singleton
    fun getLocalStorage() = LocalStorage()*/
}