package com.wangxingxing.kotlinnetworkdemo.viewmodel

import com.wangxingxing.kotlinnetworkdemo.base.BaseViewModel
import com.wangxingxing.kotlinnetworkdemo.repository.RepositoryProxy
import com.wangxingxing.network.data.wanandroid.home.HomeData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach

/**
 * author : 王星星
 * date : 2021/10/15 15:50
 * email : 1099420259@qq.com
 * description :
 */
class MainViewModel : BaseViewModel() {

    private val repository by lazy { RepositoryProxy.instance }

    private val _list = MutableStateFlow<HomeData?>(null)
    val list = _list.asStateFlow()

    fun getHomeList(page: Int) {
        flowAsyncWorkOnViewModelScopeLaunch {
            val time = System.currentTimeMillis()
            repository.callApiSuspendMethod(HomeData::class.java, "getHomeListB", page).onEach {
                _list.value = it
                errorMsgLiveData.postValue(it.data?.datas!![0].title)
                android.util.Log.e("MainViewModel", "four 444 ${it.data?.datas!![0].title}")
                android.util.Log.e("MainViewModel", "耗时:${(System.currentTimeMillis() - time)} ms")
            }
        }
    }
}