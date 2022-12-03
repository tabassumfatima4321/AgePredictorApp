package com.example.agepredictorapp.di

import com.example.agepredictorapp.manager.StringResourceManager
import com.example.agepredictorapp.network.ApiService
import com.example.agepredictorapp.network.DefaultErrorFactory
import com.example.agepredictorapp.network.IErrorFactory
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule
{
    @Provides
    @Singleton
    fun providesGson()= Gson()

    @Singleton
    @Provides
    fun providesOkHttpInterceptor()= HttpLoggingInterceptor().apply {
        level= HttpLoggingInterceptor.Level.BODY
    }
    @Provides
    @Singleton
    fun providesOkHttp(httpLoggingInterceptor: HttpLoggingInterceptor) : OkHttpClient
    {
        return OkHttpClient.Builder()
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }
    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient) : Retrofit
    {
        return Retrofit.Builder().baseUrl("https://api.agify.io")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
    @Provides
    @Singleton
    fun providesShoppingApiService(retrofit: Retrofit) : ApiService
    {
        return retrofit.create(ApiService::class.java)
    }
    @Provides
    @Singleton
    fun providesErrorFactory(stringResourceManager: StringResourceManager): IErrorFactory
    {
        return DefaultErrorFactory(stringResourceManager)
    }
}