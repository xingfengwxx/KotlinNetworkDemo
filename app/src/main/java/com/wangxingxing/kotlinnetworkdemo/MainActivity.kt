package com.wangxingxing.kotlinnetworkdemo

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.wangxingxing.kotlinnetworkdemo.bean.WxArticleBean
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
        mBinding.btnLogin.setOnClickListener {
            showLoading()
            mViewModel.login("xingfengwxx", "wxx123456")
        }

        mBinding.btnNet.setOnClickListener {
            showLoading()
            mViewModel.requestNet()
        }

        mBinding.btnNetError.setOnClickListener {
            showLoading()
            mViewModel.requestNetError()
        }

        mBinding.btnMultipleNet.setOnClickListener {
            mViewModel.requestFromNet()
        }

        mBinding.btnMultipleDb.setOnClickListener {
            mViewModel.requestFromDb()
        }
    }

    private fun initObserver() {
        mViewModel.userLiveData.observeState(this) {
            onSuccess {
                mBinding.tvNetResult.text = it.toString()
            }

            onComplete {
                dismissLoading()
            }
        }

        mViewModel.wxArticleLiveData.observeState(this) {
            onSuccess { data: List<WxArticleBean> ->
                showNetErrorPic(false)
                mBinding.tvNetResult.text = data.toString()
            }

            onFailed { code, msg ->

            }

            onException {
                showNetErrorPic(true)
            }

            onEmpty {

            }

            onComplete {
                dismissLoading()
            }
        }

        mViewModel.mediatorLiveData.observe(this) {
            showNetErrorPic(false)
            mBinding.tvNetResult.text = it.data.toString()
        }
    }

    private fun showLoading() {
        //避免接口请求返回时间太短，出现loading闪烁问题。500ms以内不会显示loading，显示loading后至少显示500ms。
        mBinding.loadingProgressBar.show()
    }

    private fun dismissLoading() {
        mBinding.loadingProgressBar.hide()
    }

    private fun showNetErrorPic(isShowError: Boolean) {
        mBinding.tvNetResult.isGone = isShowError
        mBinding.aivError.isVisible = isShowError
    }
}