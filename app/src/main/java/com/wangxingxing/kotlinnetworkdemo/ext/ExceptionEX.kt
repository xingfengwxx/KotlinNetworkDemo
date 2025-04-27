package com.wangxingxing.kotlinnetworkdemo.ext

class ExceptionEX(val errorCode: Int, val errorMessage: String) : Exception(errorMessage) {
}