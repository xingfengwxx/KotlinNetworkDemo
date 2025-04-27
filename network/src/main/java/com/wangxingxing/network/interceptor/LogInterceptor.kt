package com.wangxingxing.network.interceptor


import com.wangxingxing.network.util.LogUtils
import okhttp3.logging.HttpLoggingInterceptor
import com.wangxingxing.network.BuildConfig

/**
 * author : 王星星
 * date : 2025/4/27 17:10
 * email : 1099420259@qq.com
 * description :
 */
val logInterceptor = HttpLoggingInterceptor { message ->
    // 使用自己的日志工具接管
    LogUtils.d(tag = "Network", msg = message)
}
// 注意要生成BuildConfig类，就必须在gradle中配置buildConfig为true
    .setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.BASIC)