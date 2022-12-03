package com.example.agepredictorapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.agepredictorapp.data.AgeApiResponse


@Dao
interface UserDao {

    @Query("SELECT * FROM UserAgeInfo WHERE userName =:userName")
    suspend fun getUser(userName:String): AgeApiResponse

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user:AgeApiResponse) : Long

    @Query("SELECT * FROM UserAgeInfo")
    suspend fun getUsers(): List<AgeApiResponse>


}
