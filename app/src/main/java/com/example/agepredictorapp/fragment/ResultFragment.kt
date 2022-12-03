package com.example.agepredictorapp.fragment

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.agepredictorapp.R
import com.example.agepredictorapp.base.BaseFragment
import com.example.agepredictorapp.databinding.FragmentResultBinding
import com.example.agepredictorapp.viewmodel.DefaultUserViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ResultFragment : BaseFragment<FragmentResultBinding>()
{
    private val viewModel: DefaultUserViewModel by activityViewModels()
    override fun getLayout(): Int = R.layout.fragment_result
    override fun getViewModel(): ViewModel = viewModel
    override fun init() {
        viewModel.result.observe(requireActivity()) {
            binding?.userAge?.text = it.toString()
        }
    }


}
