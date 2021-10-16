package com.wangxingxing.kotlinnetworkdemo.net

import com.wangxingxing.kotlinnetworkdemo.bean.User
import com.wangxingxing.kotlinnetworkdemo.bean.WxArticleBean
import com.wangxingxing.network.entity.ApiResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * author : 王星星
 * date : 2021/10/15 10:39
 * email : 1099420259@qq.com
 * description :
 */
interface ApiService {

    @GET("wxarticle/chapters/json")
    suspend fun getWxArticle(): ApiResponse<List<WxArticleBean>>

    @GET("abc/chapters/json")
    suspend fun getWxArticleError(): ApiResponse<List<WxArticleBean>>

    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(@Field("username") userName: String, @Field("password") passWord: String): ApiResponse<User?>

    companion object {
        const val BASE_URL = "https://wanandroid.com/"
    }
}