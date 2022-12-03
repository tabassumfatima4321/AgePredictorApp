package com.example.agepredictorapp.fragment.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.example.agepredictorapp.R
import com.example.agepredictorapp.base.BaseFragment
import com.example.agepredictorapp.databinding.FragmentHistoryBinding
import com.example.agepredictorapp.databinding.FragmentResultBinding
import com.example.agepredictorapp.viewmodel.DefaultUserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment: BaseFragment<FragmentHistoryBinding>()
{
    private val viewModel: DefaultUserViewModel by viewModels()
    override fun getLayout(): Int = R.layout.fragment_history
    override fun getViewModel(): ViewModel = viewModel
    override fun init() {
        viewModel.getUsers()
    }


}
