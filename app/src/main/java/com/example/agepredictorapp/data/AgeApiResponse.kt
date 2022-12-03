package com.example.agepredictorapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "UserAgeInfo")
data class AgeApiResponse(
    @PrimaryKey @ColumnInfo(name = "userAge")  val age: Int,
    @ColumnInfo(name = "userCount") val count: Int,
    @ColumnInfo(name = "userName")  val name: String
)
