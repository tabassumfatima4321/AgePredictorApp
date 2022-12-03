package com.example.agepredictorapp.base

import com.example.agepredictorapp.network.IErrorFactory
import com.example.agepredictorapp.network.NetworkResponseResult
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

interface BaseRemoteDataSource
{
    suspend fun <T> getResults(call:suspend ()-> Response<T>) : NetworkResponseResult<T>
}
open class DefaultBaseRemoteDataSource() : BaseRemoteDataSource
{
    @set:Inject
    internal var errorIErrorFactory: IErrorFactory?=null
    override suspend fun <T> getResults(call: suspend () -> Response<T>): NetworkResponseResult<T> {
        return try {
            val response=call.invoke()
            return if(response.isSuccessful && response.body()!=null)
            { NetworkResponseResult.Success(response.body()!!) }
            else
            { NetworkResponseResult.EmptyResponse }
        } catch (throwable:Throwable) {
            if(throwable is IOException) {
                NetworkResponseResult.NetworkError
            } else {
                NetworkResponseResult.Failure(
                    errorIErrorFactory?.getError(throwable)?.getErrorResponse(throwable)!!)
            }
        }
    }
}