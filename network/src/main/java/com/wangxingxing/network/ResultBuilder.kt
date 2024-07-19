package com.wangxingxing.network

import android.util.Log
import com.wangxingxing.network.entity.*

/**
 * author : 王星星
 * date : 2024/7/19 15:20
 * email : 1099420259@qq.com
 * description :
 */
fun <T> ApiResponse<T>.parseData(listenerBuilder: ResultBuilder<T>.() -> Unit) {
    val listener = ResultBuilder<T>().also(listenerBuilder)
    when (this) {
        is ApiSuccessResponse -> listener.onSuccess(this.response)
        is ApiEmptyResponse -> listener.onDataEmpty()
        is ApiFailedResponse -> listener.onFailed(this.errorCode, this.errorMsg)
        is ApiErrorResponse -> listener.onError(this.throwable)
    }
    listener.onComplete()
}

class ResultBuilder<T> {
    private val TAG = "OkHttp"

    var onSuccess: (data: T?) -> Unit = {}
    var onDataEmpty: () -> Unit = {}
    var onFailed: (errorCode: Int?, errorMsg: String?) -> Unit = { _, errorMsg ->
        errorMsg?.let {
            Log.e(TAG, "onFailed: $errorMsg")
        }
    }
    var onError: (e: Throwable) -> Unit = { e ->
        e.message?.let {
            Log.e(TAG, "onError: $it")
        }
    }
    var onComplete: () -> Unit = {}
}