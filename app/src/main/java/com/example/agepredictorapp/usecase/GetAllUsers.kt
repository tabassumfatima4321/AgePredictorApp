package com.example.agepredictorapp.usecase

import com.example.agepredictorapp.base.BaseUseCase
import com.example.agepredictorapp.base.ErrorStateMapper
import com.example.agepredictorapp.base.Resource
import com.example.agepredictorapp.data.AgeApiResponse
import com.example.agepredictorapp.network.NetworkResponseResult
import com.example.agepredictorapp.repostiory.UserRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetAllUsers @Inject constructor(
    private val userRepository: UserRepository, private val errorStateMapper: ErrorStateMapper
)
    : BaseUseCase<String, Resource<List<AgeApiResponse>>> {
    override fun invoke(params: String?): Flow<Resource<List<AgeApiResponse>>> = flow {
        emit(Resource.Loading)
        val resp:List<AgeApiResponse>?=userRepository.getUsers()
        if(!resp.isNullOrEmpty()) {
            emit(Resource.Success(resp))
        }
        else {
            emit(errorStateMapper.map(NetworkResponseResult.EmptyResponse))

        }
        }.flowOn(getDispatcher())

    }

