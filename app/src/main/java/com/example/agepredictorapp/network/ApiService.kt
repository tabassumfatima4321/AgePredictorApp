package com.example.agepredictorapp.network

import com.example.agepredictorapp.data.AgeApiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("/")
    suspend fun getAge(@Query("name") name:String) : Response<AgeApiResponse>
}
