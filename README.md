# Kotlin+Retrofit+okHttp+协程 网络模块封装

MVVM官方架构图

![MVVM官方架构图](https://gitee.com/xingfengwxx/blogImage/raw/master/img/20210802152548.webp)

[原文链接](https://juejin.cn/post/6993294489125126151)，感谢作者的分享！

### 分支说明

- master：**`Kotlin`+`协程`+`LiveData`+`Retrofit`+`OkHttp`+`Repository`**
  - 优点：
    - 代码很简洁，不需要手写线程切换代码，没有很多的接口回调。
    - 自带Loading状态，不需要手动启用Loading和关闭Loading。
    - 数据驱动ui，以LiveData为载体，将页面状态和网络结果通过在LiveData返回给ui。
  - 缺点：
    - 解耦不彻底，违背了"在应用的各个模块之间设定明确定义的职责界限"的思想。
    - LiveData监听时，如果需要Loading，`BaseActivity`都需要实现带有Loading方法接口。
    - `obserState()`方法第二个参数中传入了UI引用。
    - 不能达到"看方法如其意"，如果是刚接触，会有很多疑问：为什么需要一个livedata作为方法的参数。网络请求的返回值去哪了？
    - 封装一还有一个最大的缺陷：对于是多数据源，封装一就展示了很不友好的一面。

- flow:**`Kotlin`+`协程`+`Flow`+`Retrofit`+`OkHttp`+`Repository`**
  - 解耦更彻底，可以独立于ui模块运行。