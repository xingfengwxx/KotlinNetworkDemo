package com.wangxingxing.kotlinnetworkdemo.bean

/**
 * author : 王星星
 * date : 2021/10/15 10:40
 * email : 1099420259@qq.com
 * description :
 */
class WxArticleBean {
    /**
     * id : 408
     * name : 鸿洋
     * order : 190000
     * visible : 1
     */
    var id = 0
    var name: String? = null
    var visible = 0

    override fun toString(): String {
        return "WxArticleBean(id=$id, name=$name, visible=$visible)"
    }


}