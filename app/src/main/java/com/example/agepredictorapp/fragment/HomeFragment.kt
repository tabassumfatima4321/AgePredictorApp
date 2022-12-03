package com.example.agepredictorapp.fragment


import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.agepredictorapp.R
import com.example.agepredictorapp.base.BaseFragment
import com.example.agepredictorapp.databinding.FragmentHomeBinding
import com.example.agepredictorapp.viewmodel.DefaultUserViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment: BaseFragment<FragmentHomeBinding>()
{
    private val viewModel:DefaultUserViewModel by activityViewModels()
    override fun getLayout(): Int = R.layout.fragment_home
    override fun getViewModel(): ViewModel = viewModel

    override fun init() {
        binding?.btnSearch?.setOnClickListener{
            viewModel.getUserAge(binding?.editText?.text.toString())
        }
    }



}
