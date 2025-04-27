package com.wangxingxing.network.data.wanandroid

open class CommonResult<T> {
    var data: T? = null
    var errorCode: Int? = 0
    var errorMsg: String? = null
}