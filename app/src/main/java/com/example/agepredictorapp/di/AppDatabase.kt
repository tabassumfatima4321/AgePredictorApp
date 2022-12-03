package com.example.agepredictorapp.di

import android.content.Context
import androidx.room.Database
import androidx.room.*
import com.example.agepredictorapp.data.AgeApiResponse
import com.example.agepredictorapp.database.dao.UserDao

@Database(entities = [AgeApiResponse::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}
