package uz.targetsoftwaredevelopment.myapplication.di.model

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.targetsoftwaredevelopment.myapplication.domain.repository.BaseRepository
import uz.targetsoftwaredevelopment.myapplication.domain.repository.impl.BaseRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun getBaseRepository(baseRepository: BaseRepositoryImpl): BaseRepository

/*
    @Binds
    @Singleton
    fun getAllPostsRepository(allVideosRepository: AllVideosBaseRepositoryImpl): ALlVideosBaseRepository*/
}