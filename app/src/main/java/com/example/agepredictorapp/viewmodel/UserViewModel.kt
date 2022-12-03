package com.example.agepredictorapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agepredictorapp.base.Resource
import com.example.agepredictorapp.bindingCustomAdapters.ItemViewModel
import com.example.agepredictorapp.data.AgeApiResponse
import com.example.agepredictorapp.fragment.history.adapters.HistoryAdapter
import com.example.agepredictorapp.usecase.GetAllUsers
import com.example.agepredictorapp.usecase.GetUserAgeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

interface UserViewModel
{
    fun getUserAge(userName:String?)
    fun getUsers()
    fun createHistoryRecyclerViewData( ageApiResponse: AgeApiResponse,position: Int) : ItemViewModel
}
@HiltViewModel
class DefaultUserViewModel @Inject constructor(private val useCase: GetUserAgeUseCase,
private val getAllUsers: GetAllUsers)
    :ViewModel(),UserViewModel
{
    private val _loader by lazy { MutableLiveData<Boolean>() }
    val loader: LiveData<Boolean> get() = _loader
    private val _result by lazy { MutableLiveData<String>() }
    val result: LiveData<String> get() = _result
    private val _itemViewModel by lazy { MutableLiveData<List<ItemViewModel>>(emptyList()) }
    val itemViewModel: LiveData<List<ItemViewModel>> get() = _itemViewModel
    override fun getUserAge(userName: String?) {
        viewModelScope.launch {
            useCase(userName).collectLatest {
                when(it)
                {

                    Resource.Loading ->
                    {
                        _loader.postValue(true)
                    }
                    is Resource.Success -> {
                        _loader.postValue(false)
                        _result.postValue(it.data.toString())
                    }
                    is Resource.Error -> {
                        _loader.postValue(false)
                        _result.postValue(it.viewError.message)

                    }
                }
            }
        }
    }

    override fun getUsers() {
        viewModelScope.launch {
            getAllUsers().collectLatest {
                when(it)
                {

                    Resource.Loading ->
                    {
                        _loader.postValue(true)
                    }
                    is Resource.Success -> {
                        _loader.postValue(false)
                        val viewData = mutableListOf<ItemViewModel>()
                        (it.data as List<AgeApiResponse>).apply {
                            for (i in 0 until this.size) {
                                viewData.add(
                                    createHistoryRecyclerViewData(
                                        this[i], i
                                    )
                                )
                            }
                            _itemViewModel.postValue(viewData)
                        }
                    }
                    is Resource.Error -> {
                        _loader.postValue(false)
                        _result.postValue(it.viewError.message)

                    }
                }
            }
        }
    }

    override fun createHistoryRecyclerViewData( ageApiResponse: AgeApiResponse,position: Int)
            : ItemViewModel {
        return HistoryAdapter(ageApiResponse)
    }
    companion object {
        const val HISTORY_ITEM = 0
    }
}
