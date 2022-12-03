package com.example.agepredictorapp.di

import com.example.agepredictorapp.database.dao.UserDao
import com.example.agepredictorapp.datasource.local.DefaultLocalUserDataSource
import com.example.agepredictorapp.datasource.local.UserLocalDataSource
import com.example.agepredictorapp.datasource.remote.DefaultUserRemoteDataSource
import com.example.agepredictorapp.datasource.remote.UserRemoteDataSource
import com.example.agepredictorapp.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule
{
    @Singleton
    @Provides
    fun providesUserRemoteDataSource(apiService: ApiService) : UserRemoteDataSource
    =DefaultUserRemoteDataSource(apiService)

    @Singleton
    @Provides
    fun providesUserLocalDataSource(dao: UserDao) : UserLocalDataSource
    {
        return DefaultLocalUserDataSource(dao)
    }
}
