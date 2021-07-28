# 一个项目如何编译多个不同签名、包名、资源、依赖等，的apk？
文章地址：[https://blog.xujiaji.com/post/android-project-one-for-more](https://blog.xujiaji.com/post/android-project-one-for-more)

该项目为该文章Demo

> 其他的一些问题整理

1. [微信包名问题请参照这里](https://github.com/xujiaji/OneForAllApk/issues/1#issuecomment-440520289)
2. [sharedUserId变更配置](https://github.com/xujiaji/OneForAllApk/issues/4#issuecomment-444491414)

## 运行时选择渠道
<img  src="https://raw.githubusercontent.com/xujiaji/xujiaji.github.io/pictures/blog/one-for-more/20181113182242.png" width="22%" height="auto">

## 选择不同渠道将运行不同的apk
<img  src="https://raw.githubusercontent.com/xujiaji/xujiaji.github.io/pictures/blog/one-for-more/Screenshot_2018-11-13-18-20-13-184_com.miui.home.png" width="22%" height="auto">

## 这些apk，都有不同的资源配置
- 不同的应用名称
- 不同的版本
- 不同的java文件
- 不同的图片资源
- 不同的文本资源
- 不同的assets文件资源
- 不同的网络资源
- 不同的主题颜色
- 不同的第三方key
- 不同的依赖类库
- 等等

<div >
<img  src="https://raw.githubusercontent.com/xujiaji/xujiaji.github.io/pictures/blog/one-for-more/20181113182506.png" width="33%" height="auto">
<img style="margin-left:0px;" src="https://raw.githubusercontent.com/xujiaji/xujiaji.github.io/pictures/blog/one-for-more/20181113182534.png" width="33%" >
<img style="margin-left:0px;" src="https://raw.githubusercontent.com/xujiaji/xujiaji.github.io/pictures/blog/one-for-more/20181113182556.png" width="33%" >
</div>


## 后期更新的配置
#### library中也需要动态改变资源配置，并且打包如何加上中文名 [#3](https://github.com/xujiaji/OneForAllApk/issues/3)
1. 如需要中文名，只需要switch语句为对应渠道添加上即可，`outputFileName`拼接的结果就是最终的文件名
``` groovy
    applicationVariants.all {
        variant ->
            variant.outputs.all {
                def chineseName = ""
                switch(variant.productFlavors[0].name) {
                    case "oneApk":
                        chineseName = "第一个APK"
                        break
                    case "twoApk":
                        chineseName = "第二个APK"
                        break
                    case "threeApk":
                        chineseName = "第三个APK"
                        break
                }
                outputFileName = "${chineseName}-${variant.productFlavors[0].name}-v${variant.productFlavors[0].versionName}-${releaseTime()}.apk"
            }
    }
```
2. 下图展示了对library的配置，只需在library也添加上对应的渠道和文件夹即可（和主模块同理）。需注意如果使用library的`BuildConfig`注意包名，如本例子中的`MainActivity`使用了全路径`com.xujiaji.library.BuildConfig`
<img  src="https://raw.githubusercontent.com/xujiaji/xujiaji.github.io/pictures/blog/one-for-more/20181129192318.png" width="33%" height="auto">

