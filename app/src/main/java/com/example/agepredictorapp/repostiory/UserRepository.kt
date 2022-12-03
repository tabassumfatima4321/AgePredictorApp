package com.example.agepredictorapp.repostiory

import com.example.agepredictorapp.data.AgeApiResponse
import com.example.agepredictorapp.datasource.local.UserLocalDataSource
import com.example.agepredictorapp.datasource.remote.UserRemoteDataSource
import com.example.agepredictorapp.network.NetworkResponseResult
import javax.inject.Inject

interface UserRepository {
    suspend fun getUserAge(name:String) : NetworkResponseResult<AgeApiResponse>
    suspend fun getUser(userName:String): AgeApiResponse
    suspend fun insert(user:AgeApiResponse) : Long
    suspend fun getUsers(): List<AgeApiResponse>

}
class DefaultUserRepository @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
,private val userLocalDataSource: UserLocalDataSource) : UserRepository
{
    override suspend fun getUserAge(name: String): NetworkResponseResult<AgeApiResponse> = userRemoteDataSource.getUserAge(name)


    override suspend fun getUser(userName: String): AgeApiResponse=userLocalDataSource.getUser(userName)

    override suspend fun insert(user: AgeApiResponse): Long = userLocalDataSource.insert(user)
    override suspend fun getUsers(): List<AgeApiResponse> {
        return userLocalDataSource.getUsers()
    }

}
