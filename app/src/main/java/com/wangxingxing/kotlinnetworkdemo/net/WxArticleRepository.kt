package com.wangxingxing.kotlinnetworkdemo.net

import com.wangxingxing.kotlinnetworkdemo.bean.User
import com.wangxingxing.kotlinnetworkdemo.bean.WxArticleBean
import com.wangxingxing.network.base.BaseRepository
import com.wangxingxing.network.entity.ApiResponse
import com.wangxingxing.network.entity.ApiSuccessResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * author : 王星星
 * date : 2021/10/15 15:57
 * email : 1099420259@qq.com
 * description :
 */
class WxArticleRepository : BaseRepository() {

    private val mService by lazy {
        RetrofitClient.service
    }

    suspend fun login(username: String, password: String): ApiResponse<User?> {
        return executeHttp {
            mService.login(username, password)
        }
    }

    suspend fun fetchWxArticleFromNet(): ApiResponse<List<WxArticleBean>> {
        return executeHttp {
            mService.getWxArticle()
        }
    }

    suspend fun fetchWxArticleError(): ApiResponse<List<WxArticleBean>> {
        return executeHttp {
            mService.getWxArticleError()
        }
    }

    suspend fun fetchWxArticleFromDb(): ApiResponse<List<WxArticleBean>> {
        return getWxArticleFromDatabase()
    }

    private suspend fun getWxArticleFromDatabase(): ApiResponse<List<WxArticleBean>> = withContext(Dispatchers.IO) {
        val bean = WxArticleBean()
        bean.id = 666
        bean.name = "尼古拉斯星爷"
        bean.visible = 1
        ApiSuccessResponse(arrayListOf(bean))
    }
}