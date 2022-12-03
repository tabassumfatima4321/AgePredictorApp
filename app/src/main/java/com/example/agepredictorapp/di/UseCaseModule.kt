package com.example.agepredictorapp.di

import com.example.agepredictorapp.base.ErrorStateMapper
import com.example.agepredictorapp.repostiory.UserRepository
import com.example.agepredictorapp.usecase.GetAllUsers
import com.example.agepredictorapp.usecase.GetUserAgeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun providesGetAllUserUseCase(
        userRepository: UserRepository,
        errorStateMapper: ErrorStateMapper
    ) : GetAllUsers
            =GetAllUsers(userRepository,errorStateMapper)
    @Provides
    @Singleton
    fun providesGetAgeUseCase(
        userRepository: UserRepository,
        errorStateMapper: ErrorStateMapper
    ) : GetUserAgeUseCase
    =GetUserAgeUseCase(userRepository,errorStateMapper)
}
