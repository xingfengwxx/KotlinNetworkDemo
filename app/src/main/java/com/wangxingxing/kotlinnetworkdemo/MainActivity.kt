package com.wangxingxing.kotlinnetworkdemo

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.wangxingxing.kotlinnetworkdemo.databinding.ActivityMainBinding
import com.wangxingxing.kotlinnetworkdemo.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val mBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val mViewModel by viewModels<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)

        init()
        initObserver()
    }

    private fun init() {
        mViewModel.getHomeList(1)
    }

    private fun initObserver() {
        lifecycleScope.launchWhenStarted {
            mViewModel.list.collect() {
                mBinding.tvNetResult.text = it.toString()
            }
        }
    }

//    private fun showLoading() {
//        //避免接口请求返回时间太短，出现loading闪烁问题。500ms以内不会显示loading，显示loading后至少显示500ms。
//        mBinding.loadingProgressBar.show()
//    }
//
//    private fun dismissLoading() {
//        mBinding.loadingProgressBar.hide()
//    }

    private fun showNetErrorPic(isShowError: Boolean) {
        mBinding.tvNetResult.isGone = isShowError
        mBinding.aivError.isVisible = isShowError
    }

    companion object {
        const val TAG = "wxx"
    }

//    override fun showLoading() {
//        //避免接口请求返回时间太短，出现loading闪烁问题。500ms以内不会显示loading，显示loading后至少显示500ms。
//        mBinding.loadingProgressBar.show()
//    }
//
//    override fun dismissLoading() {
//        mBinding.loadingProgressBar.hide()
//    }
}