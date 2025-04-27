package com.wangxingxing.kotlinnetworkdemo

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.wangxingxing.kotlinnetworkdemo.base.BaseViewModelActivity
import com.wangxingxing.kotlinnetworkdemo.databinding.ActivityMainBinding
import com.wangxingxing.kotlinnetworkdemo.viewmodel.MainViewModel

class MainActivity : BaseViewModelActivity<MainViewModel>(R.layout.activity_main) {

    private var tvNetResult: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tvNetResult = findViewById(R.id.tv_net_result)

        init()
        initObserver()
    }

    private fun init() {
        viewModel.getHomeList(1)
    }

    private fun initObserver() {
        lifecycleScope.launchWhenStarted {
            viewModel.list.collect() {
                tvNetResult?.text = it?.data?.datas.toString()
            }
        }
    }

    companion object {
        const val TAG = "wxx"
    }

}