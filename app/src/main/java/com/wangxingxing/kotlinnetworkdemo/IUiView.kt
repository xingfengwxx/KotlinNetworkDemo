package com.wangxingxing.kotlinnetworkdemo

import androidx.lifecycle.LifecycleOwner

interface IUiView : LifecycleOwner {

    fun showLoading()

    fun dismissLoading()
}