package com.example.idusassignment.ui.main

import android.os.Bundle
import com.example.idusassignment.R
import com.example.idusassignment.databinding.ActivityMainBinding
import com.example.idusassignment.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding {
            mainViewModel.searchCity()
        }
    }
}