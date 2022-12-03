package com.example.agepredictorapp.fragment.history.adapters

import com.example.agepredictorapp.R
import com.example.agepredictorapp.bindingCustomAdapters.ItemViewModel
import com.example.agepredictorapp.data.AgeApiResponse
import com.example.agepredictorapp.viewmodel.DefaultUserViewModel

class HistoryAdapter constructor( val ageApiResponse: AgeApiResponse)
    : ItemViewModel {
    override val layoutId: Int get() = R.layout.item_user
    override val viewType: Int
        get() = DefaultUserViewModel.HISTORY_ITEM

    override fun areItemsTheSame(other: ItemViewModel): Boolean =this===other

    override fun areContentTheSame(other: ItemViewModel): Boolean {
        return if(other is HistoryAdapter) {
            other.ageApiResponse==ageApiResponse
        } else false
    }
}
