package com.wangxingxing.kotlinnetworkdemo.repository

import com.wangxingxing.kotlinnetworkdemo.base.BaseRepositoryProxy
import com.wangxingxing.network.net.NetApi
import com.wangxingxing.network.RetrofitUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import kotlin.reflect.full.callSuspend

class RepositoryProxy private constructor() : BaseRepositoryProxy() {

    val service = NetApi::class.java
    val api by lazy { RetrofitUtils.instance.create(service) }


    companion object {
        val instance by lazy { RepositoryProxy() }
    }

    fun <R> callApiMethod(clazzR: Class<R>, methodName: String, vararg args: Any): Flow<R> {
        return flow {
            val clssss = mutableListOf<Class<out Any>>()
            args?.forEach {
                clssss.add(javaClassTransform(it.javaClass))
            }
            val parameterTypes = clssss.toTypedArray()
            val call = (service.getMethod(methodName, *parameterTypes)?.invoke(api, *(args ?: emptyArray())) as Call<R>)
            call?.execute()?.body()?.let {
                emit(it as R)
            }
        }
    }

    @OptIn(ExperimentalStdlibApi::class)
    fun <R> callApiSuspendMethod(clazzR: Class<R>, methodName: String, vararg args: Any): Flow<R> {
        return flow {
            val funcds = findSuspendMethod(service, methodName, args)
            if (args == null) {
                emit(funcds?.callSuspend(api) as R)
            } else {
                emit(funcds?.callSuspend(api, *args) as R)
            }
        }
    }
}