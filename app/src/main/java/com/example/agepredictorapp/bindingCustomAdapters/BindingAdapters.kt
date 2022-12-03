package com.example.agepredictorapp.bindingCustomAdapters

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView


@BindingAdapter("android:visibility")
fun setVisibility(view: View, value: Boolean) {
    view.visibility = if (value) View.VISIBLE else View.GONE
}
@BindingAdapter("android:setText")
fun setText(view: TextView, text:String?) {
    text?.let {
        view.text = text
    }
}

/*Recyclerview work*/
fun createOrUpdateRecyclerView(recyclerView: RecyclerView): RecyclerviewAdapter
{
    return if(recyclerView.adapter!=null && recyclerView.adapter is RecyclerviewAdapter)
    {
        recyclerView.adapter as RecyclerviewAdapter
    }
    else
    {
        val recyclerViewAdapter=RecyclerviewAdapter()
        recyclerView.adapter=recyclerViewAdapter
        recyclerViewAdapter
    }
}
@BindingAdapter("set_recycler_view_items")
fun bindRecyclerViewItems(recyclerView: RecyclerView, itemViewModel: List<ItemViewModel>?)
{
    val recyclerviewAdapter= createOrUpdateRecyclerView(recyclerView)
    recyclerviewAdapter.updateItems(itemViewModel)
}
