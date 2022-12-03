package com.example.agepredictorapp.datasource.local

import com.example.agepredictorapp.data.AgeApiResponse
import com.example.agepredictorapp.database.dao.UserDao
import javax.inject.Inject

interface UserLocalDataSource{
    suspend fun getUser(userName:String): AgeApiResponse
    suspend fun insert(user:AgeApiResponse) : Long
    suspend fun getUsers(): List<AgeApiResponse>

}
class  DefaultLocalUserDataSource @Inject constructor(private val dao: UserDao) : UserLocalDataSource
{
    override suspend fun getUser(userName: String): AgeApiResponse {
     return dao.getUser(userName)
    }

    override suspend fun insert(user: AgeApiResponse) : Long{
       return dao.insert(user)
    }

    override suspend fun getUsers(): List<AgeApiResponse> {
        return dao.getUsers()

    }

}
