package com.example.idusassignment.ui.main

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.idusassignment.R
import com.example.idusassignment.databinding.ActivityMainBinding
import com.example.idusassignment.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val mainViewModel: MainViewModel by viewModel()
    private val mainAdapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding {
            mainVm = mainViewModel
            rvWeather.adapter = mainAdapter

            rvWeather.addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    LinearLayout.VERTICAL
                ).apply {
                    this@MainActivity.getDrawable(R.drawable.bg_divider)?.let {
                        setDrawable(it)
                    }
                }
            )

            mainViewModel.searchCity()

            refresh.setOnRefreshListener {
                reLoadData()
                refresh.isRefreshing = false
            }
        }

        observing {
            errorMsg.observe(this@MainActivity, Observer {
                showErrorMsg(it)
            })
        }
    }

    private fun observing(action: MainViewModel.() -> Unit) {
        mainViewModel.run(action)
    }

    private fun showErrorMsg(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun reLoadData() {
        mainViewModel.searchCity()
        mainAdapter.clearList()
    }
}