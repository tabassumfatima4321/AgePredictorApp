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

class GetUserAgeUseCase @Inject constructor(
    private val userRepository: UserRepository,private val errorStateMapper: ErrorStateMapper
)
    : BaseUseCase<String,Resource<Int>>
{
    override fun invoke(params: String?): Flow<Resource<Int>> = flow {
        emit(Resource.Loading)
        params?.let {name->
            val dbResponse: AgeApiResponse? =userRepository.getUser(name)
            dbResponse?.let{
                emit(Resource.Success(it.age))

            }?: run {
                when (val resp = userRepository.getUserAge(name)) {
                    is NetworkResponseResult.Success -> {
                        val result=userRepository.insert(resp.data)
                        if(result!=-1)emit(Resource.Success(resp.data.age))
                    }
                    else -> {
                        emit(errorStateMapper.map(NetworkResponseResult.EmptyResponse))
                    }
                }

            }

        }?: run {
            emit(errorStateMapper.map(null))

        }
    }.flowOn(getDispatcher())

}
