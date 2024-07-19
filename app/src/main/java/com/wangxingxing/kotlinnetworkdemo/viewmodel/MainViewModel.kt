package com.wangxingxing.kotlinnetworkdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wangxingxing.kotlinnetworkdemo.bean.User
import com.wangxingxing.kotlinnetworkdemo.bean.WxArticleBean
import com.wangxingxing.kotlinnetworkdemo.net.WxArticleRepository
import com.wangxingxing.network.entity.ApiResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

/**
 * author : 王星星
 * date : 2021/10/15 15:50
 * email : 1099420259@qq.com
 * description :
 */
class MainViewModel : ViewModel() {

    private val repository by lazy { WxArticleRepository() }

    private val _userLiveData = MutableStateFlow<ApiResponse<User?>>(ApiResponse())
    val userLiveData: StateFlow<ApiResponse<User?>> = _userLiveData.asStateFlow()

    private val _wxArticleLiveData =
        MutableStateFlow<ApiResponse<List<WxArticleBean>>>(ApiResponse())
    val wxArticleLiveData: StateFlow<ApiResponse<List<WxArticleBean>>> =
        _wxArticleLiveData.asStateFlow()

    private val _apiLiveData = MutableStateFlow<ApiResponse<List<WxArticleBean>>>(ApiResponse())
    private val apiLiveData: StateFlow<ApiResponse<List<WxArticleBean>>> = _apiLiveData.asStateFlow()

    private val _dbLiveData = MutableStateFlow<ApiResponse<List<WxArticleBean>>>(ApiResponse())
    private val dbLiveData: StateFlow<ApiResponse<List<WxArticleBean>>> = _dbLiveData.asStateFlow()

    val _mediatorLiveData =
        MutableStateFlow<ApiResponse<List<WxArticleBean>>>(ApiResponse()).apply {
//        this.addSource(apiLiveData) {
//            this.value = it
//        }
//        this.addSource(dbLiveData) {
//            this.value = it
//        }
        }

    val mediatorLiveData: StateFlow<ApiResponse<List<WxArticleBean>>> =
        _mediatorLiveData.asStateFlow()

    suspend fun login(username: String, password: String): ApiResponse<User?> {
        return repository.login(username, password)
    }

    fun requestNet() {
        viewModelScope.launch {
            _wxArticleLiveData.value = repository.fetchWxArticleFromNet()
        }
    }
    suspend fun requestNetError(): ApiResponse<List<WxArticleBean>> {
        return repository.fetchWxArticleError()
    }

//    fun requestFromNet() {
//        viewModelScope.launch {
//            _apiLiveData.value = repository.fetchWxArticleFromNetByFlow()
//        }
//    }
//
//    fun requestFromDb() {
//        viewModelScope.launch {
//            _dbLiveData.value = repository.fetchWxArticleFromDbByFlow()
//        }
//    }

    fun test() {
        viewModelScope.launch {
            val testFlow = combine(
                repository.fetchWxArticleFromNetByFlow(),
                repository.fetchWxArticleFromNetByFlow()
            ) { a, b ->

            }.collect()

        }
    }

}