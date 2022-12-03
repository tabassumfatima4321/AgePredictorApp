package com.example.agepredictorapp.di

import android.content.Context
import com.example.agepredictorapp.base.DefaultErrorStateMapper
import com.example.agepredictorapp.base.ErrorStateMapper
import com.example.agepredictorapp.manager.DefaultStringResourceManager
import com.example.agepredictorapp.manager.DefaultToastManager
import com.example.agepredictorapp.manager.StringResourceManager
import com.example.agepredictorapp.manager.ToastManager
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesToastManager(@ApplicationContext context:Context
    ,stringResourceManager: StringResourceManager):ToastManager
    =DefaultToastManager(context,stringResourceManager)
    @Provides
    @Singleton
    fun providesStringResourceManager(
        @ApplicationContext context:Context):StringResourceManager
    =DefaultStringResourceManager(context)
    @Provides
    @Singleton
    fun providesErrorStateMapper(stringResourceManager: StringResourceManager
    ,gson: Gson):ErrorStateMapper=
        DefaultErrorStateMapper(stringResourceManager,gson)
}
