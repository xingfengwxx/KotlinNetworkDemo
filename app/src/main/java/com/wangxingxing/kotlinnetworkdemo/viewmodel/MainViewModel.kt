package com.wangxingxing.kotlinnetworkdemo.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wangxingxing.kotlinnetworkdemo.bean.User
import com.wangxingxing.kotlinnetworkdemo.bean.WxArticleBean
import com.wangxingxing.kotlinnetworkdemo.net.WxArticleRepository
import com.wangxingxing.network.entity.ApiResponse
import com.wangxingxing.network.observer.StateLiveData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * author : 王星星
 * date : 2021/10/15 15:50
 * email : 1099420259@qq.com
 * description :
 */
class MainViewModel : ViewModel() {

    private val repository by lazy { WxArticleRepository() }

    val userLiveData = StateLiveData<User?>()
    val wxArticleLiveData = StateLiveData<List<WxArticleBean>>()

    private val apiLiveData = StateLiveData<List<WxArticleBean>>()
    private val dbLiveData = StateLiveData<List<WxArticleBean>>()

    val mediatorLiveData = MediatorLiveData<ApiResponse<List<WxArticleBean>>>().apply {
        this.addSource(apiLiveData) {
            this.value = it
        }
        this.addSource(dbLiveData) {
            this.value = it
        }
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            //for test
            delay(500)
            userLiveData.value = repository.login(username, password)
        }
    }

    fun requestNet() {
        viewModelScope.launch {
            wxArticleLiveData.value = repository.fetchWxArticleFromNet()
        }
    }

    fun requestNetError() {
        viewModelScope.launch {
            wxArticleLiveData.value = repository.fetchWxArticleError()
        }
    }

    fun requestFromNet() {
        viewModelScope.launch {
            apiLiveData.value = repository.fetchWxArticleFromNet()
        }
    }

    fun requestFromDb() {
        viewModelScope.launch {
            dbLiveData.value = repository.fetchWxArticleFromDb()
        }
    }

}