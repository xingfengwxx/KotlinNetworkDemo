package com.wangxingxing.kotlinnetworkdemo

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import com.wangxingxing.kotlinnetworkdemo.bean.WxArticleBean
import com.wangxingxing.kotlinnetworkdemo.databinding.ActivityMainBinding
import com.wangxingxing.kotlinnetworkdemo.ext.collectIn
import com.wangxingxing.kotlinnetworkdemo.ext.launchAndCollect
import com.wangxingxing.kotlinnetworkdemo.ext.launchWithLoadingAndCollect
import com.wangxingxing.kotlinnetworkdemo.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(), IUiView {

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
            launchWithLoadingAndCollect({
                mViewModel.login("xingfengwxx", "wxx123456")
            }) {
                onSuccess = {
                    mBinding.tvNetResult.text = it.toString()
                }
                onFailed = { errorCode, errorMsg ->
                    Log.e(TAG, "errorCode: $errorCode   errorMsg: $errorMsg")
                }
            }
        }

        mBinding.btnNet.setOnClickListener {
            mViewModel.requestNet()
        }

        mBinding.btnNetError.setOnClickListener {
            launchAndCollect({
                mViewModel.requestNetError()
            }) {
                onSuccess = {
                    showNetErrorPic(false)
                    mBinding.tvNetResult.text = it.toString()
                }

                onDataEmpty = {  }

                onComplete = { Log.i(TAG, ": onComplete") }

                onFailed = { code, msg -> Log.e(TAG, "errorCode: $code   errorMsg: $msg") }

                onError = { showNetErrorPic(true) }
            }
        }

//        mBinding.btnMultipleNet.setOnClickListener {
//            mViewModel.requestFromNet()
//        }
//
//        mBinding.btnMultipleDb.setOnClickListener {
//            mViewModel.requestFromDb()
//        }
    }

    private fun initObserver() {

        mViewModel.wxArticleLiveData.collectIn(this, Lifecycle.State.STARTED) {
            onSuccess = {
                showNetErrorPic(false)
                mBinding.tvNetResult.text = it.toString()
            }

            onDataEmpty = {  }

            onComplete = { Log.i(TAG, ": onComplete") }

            onFailed = { code, msg -> Log.e(TAG, "errorCode: $code   errorMsg: $msg") }

            onError = { showNetErrorPic(true) }
        }

//        mViewModel.mediatorLiveData.observe(this) {
//            showNetErrorPic(false)
//            mBinding.tvNetResult.text = it.data.toString()
//        }
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

    override fun showLoading() {
        //避免接口请求返回时间太短，出现loading闪烁问题。500ms以内不会显示loading，显示loading后至少显示500ms。
        mBinding.loadingProgressBar.show()
    }

    override fun dismissLoading() {
        mBinding.loadingProgressBar.hide()
    }
}