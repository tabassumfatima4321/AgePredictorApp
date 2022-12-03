package com.example.agepredictorapp.network

data class NetworkResponseError(val path:String="",val message:String,val code:Int=-1)
sealed class NetworkResponseResult<out T>
{
    data class Success<out T>(val data:T) : NetworkResponseResult<T>()
    data class Failure(val errorResponse:NetworkResponseError) : NetworkResponseResult<Nothing>()
    object  NetworkError : NetworkResponseResult<Nothing>()
    object  EmptyResponse : NetworkResponseResult<Nothing>()

}