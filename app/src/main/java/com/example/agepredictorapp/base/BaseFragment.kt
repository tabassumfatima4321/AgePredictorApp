package com.example.agepredictorapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.*
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class BaseFragment<VB: ViewDataBinding> : Fragment() {
    private  var _binding: VB?=null
    protected val binding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return getBindView(inflater,container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    abstract fun getLayout():Int
    abstract fun getViewModel(): ViewModel

    abstract fun init()
    private fun getBindView(inflater: LayoutInflater, container: ViewGroup?): View?
    {
        _binding= DataBindingUtil.inflate(inflater,getLayout(),container,false)
        return _binding?.apply {
            lifecycleOwner=viewLifecycleOwner
            setVariable(BR.viewModel,getViewModel())
        }?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}
