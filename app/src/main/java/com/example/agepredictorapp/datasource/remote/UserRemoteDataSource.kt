package com.example.agepredictorapp.datasource.remote

import com.example.agepredictorapp.base.DefaultBaseRemoteDataSource
import com.example.agepredictorapp.data.AgeApiResponse
import com.example.agepredictorapp.network.ApiService
import com.example.agepredictorapp.network.NetworkResponseResult
import javax.inject.Inject


interface UserRemoteDataSource
{
    suspend fun getUserAge(name:String) : NetworkResponseResult<AgeApiResponse>

}
class DefaultUserRemoteDataSource @Inject constructor(private val apiService: ApiService)
    : DefaultBaseRemoteDataSource(),UserRemoteDataSource
{
    override suspend fun getUserAge(name: String): NetworkResponseResult<AgeApiResponse> {
        return getResults{ apiService.getAge(name)}
    }

}