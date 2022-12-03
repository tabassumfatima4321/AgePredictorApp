package com.example.agepredictorapp.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

interface BaseUseCase<in P,R> {

     operator fun  invoke(params:P?=null): Flow<R>
    fun getDispatcher(): CoroutineDispatcher = Dispatchers.IO

}