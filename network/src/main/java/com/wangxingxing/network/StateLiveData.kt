package com.wangxingxing.network

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wangxingxing.network.entity.ApiResponse

/**
 * author : 王星星
 * date : 2024/7/19 16:53
 * email : 1099420259@qq.com
 * description :
 */
typealias StateLiveData<T> = LiveData<ApiResponse<T>>
typealias StateMutableLiveData<T> = MutableLiveData<ApiResponse<T>>

@MainThread
fun <T> StateMutableLiveData<T>.observeState(
    owner: LifecycleOwner,
    listenerBuilder: ResultBuilder<T>.() -> Unit,
) {
    observe(owner) { apiResponse -> apiResponse.parseData(listenerBuilder) }
}

@MainThread
fun <T> LiveData<ApiResponse<T>>.observeState(
    owner: LifecycleOwner,
    listenerBuilder: ResultBuilder<T>.() -> Unit,
) {
    observe(owner) { apiResponse -> apiResponse.parseData(listenerBuilder) }
}