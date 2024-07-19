package com.wangxingxing.network.entity

import android.util.Log
import com.google.gson.JsonParseException
import com.wangxingxing.network.Const.TAG
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.util.concurrent.CancellationException

enum class HttpError(var code: Int, var errorMsg: String) {
    TOKEN_EXPIRE(3001, "token is expired"),
    PARAMS_ERROR(4003, "params is error")
    // ...... more
}

internal fun handlingApiExceptions(code: Int?, errorMsg: String?) = when (code) {
    HttpError.TOKEN_EXPIRE.code -> Log.e(TAG, HttpError.TOKEN_EXPIRE.errorMsg)
    HttpError.PARAMS_ERROR.code -> Log.e(TAG, HttpError.PARAMS_ERROR.errorMsg)
    else -> errorMsg?.let { Log.e(TAG, it) }
}

internal fun handlingExceptions(e: Throwable) = when (e) {
    is HttpException -> Log.e(TAG, e.message())

    is CancellationException ->  Log.e(TAG, "CancellationException")
    is SocketTimeoutException ->  Log.e(TAG, "SocketTimeoutException")
    is JsonParseException -> Log.e(TAG, "JsonParseException")
    else -> Log.e(TAG, "Unknown error")
}