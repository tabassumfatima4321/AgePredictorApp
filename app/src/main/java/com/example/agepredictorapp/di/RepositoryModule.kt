package com.example.agepredictorapp.di

import com.example.agepredictorapp.datasource.local.UserLocalDataSource
import com.example.agepredictorapp.datasource.remote.DefaultUserRemoteDataSource
import com.example.agepredictorapp.datasource.remote.UserRemoteDataSource
import com.example.agepredictorapp.repostiory.DefaultUserRepository
import com.example.agepredictorapp.repostiory.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule
{
    @Singleton
    @Provides
    fun providesUserRepository(
        userLocalDataSource: UserLocalDataSource,
        userRemoteDataSource: UserRemoteDataSource) : UserRepository
    {
        return DefaultUserRepository(userRemoteDataSource
        ,userLocalDataSource)
    }
}
