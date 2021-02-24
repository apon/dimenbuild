

# DimenBuild [![](https://jitpack.io/v/apon/dimenbuild.svg)](https://jitpack.io/#apon/dimenbuild)

DimenBuild是一个Gradle插件，为基于smallest width限定符的Android[屏幕适配方案](https://juejin.cn/post/6844903621855805448)生成尺寸文件！



## 作用

为基于smallest width限定符的Android[屏幕适配方案](https://juejin.cn/post/6844903621855805448)生成尺寸文件！

![](../img/11.png)
![](../img/22.png)


```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <dimen name="base_swdp">410dp</dimen>
    <dimen name="_0swdp">0.0dp</dimen>
    <dimen name="_1swdp">0.59dp</dimen>
    <dimen name="_2swdp">1.17dp</dimen>
    <dimen name="_3swdp">1.76dp</dimen>
    <dimen name="_4swdp">2.34dp</dimen>
    <dimen name="_5swdp">2.93dp</dimen>
    <dimen name="_6swdp">3.51dp</dimen>
    <dimen name="_7swdp">4.1dp</dimen>
    <dimen name="_8swdp">4.69dp</dimen>
    <!--省略-->
    <dimen name="_696swdp">407.66dp</dimen>
    <dimen name="_697swdp">408.24dp</dimen>
    <dimen name="_698swdp">408.83dp</dimen>
    <dimen name="_699swdp">409.41dp</dimen>
    <dimen name="_700swdp">410.0dp</dimen>
</resources>
```

## 用法

1.修改Project的build.gradle文件

```java
buildscript {
    
    repositories {
        google()
        jcenter()
        //新加
        maven { url 'https://jitpack.io' }
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.6.1'
        //新加
        classpath 'com.github.apon:dimenbuild:1.0.0'
    }
}
```

2.修改Module的build.gradle文件


```java
apply plugin: 'com.android.application'
//新加
apply plugin: 'com.github.apon.dimenbuild'
android{}
//新加
dimenBuild{
    //设计宽度
    designWidth = 700
    //适配的最小屏幕宽度
    smallestWidth = [400,410,420,430,440,1024]
}
dependencies{}
```
3.Rebuild Project 生成dimen.xml文件

4.使用dimen资源

```xml
<!--设计宽度700px,图片宽高100px-->
<ImageView
        android:layout_width="@dimen/_100swdp"
        android:layout_height="@dimen/_100swdp"/>
```



