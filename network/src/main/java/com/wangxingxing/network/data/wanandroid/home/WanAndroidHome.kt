package com.wangxingxing.network.data.wanandroid.home

data class WanAndroidHome(
    val datas: MutableList<HomeItemBean>,
    val curPage: Int,
    val size: Int,
    val total: Int
)