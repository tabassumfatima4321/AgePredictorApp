package com.example.agepredictorapp


import androidx.navigation.ui.setupWithNavController
import com.example.agepredictorapp.base.BaseActivity
import com.example.agepredictorapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun init()
    {
        setNavHostFragment(R.id.nav_host_fragment_home)
        setNavController()
        navController?.let { binding.bottomNavigation.setupWithNavController(it) }
    }

    override fun getLayout(): Int =R.layout.activity_main
}
