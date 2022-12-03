package com.example.agepredictorapp.base

import android.annotation.SuppressLint
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T> constructor(private val
viewDataBinding: ViewDataBinding
) : RecyclerView.ViewHolder(viewDataBinding.root)
{
    abstract fun bind(position:Int,data:T)
}
abstract class BaseAdapter<T>() : RecyclerView.Adapter<BaseViewHolder<T>>()
{
    var listOfItems:MutableList<T>?= mutableListOf()
    @SuppressLint("NotifyDataSetChanged")
    set(value) {
        field=value
        notifyDataSetChanged()
    }



    override fun onBindViewHolder(
        holder: BaseViewHolder<T>,
        position: Int,
        payloads: MutableList<Any>
    ) {
        listOfItems?.get(position)?.let { holder.bind(position, it) }
    }

    override fun getItemCount(): Int = listOfItems?.size ?:run{0}
}
