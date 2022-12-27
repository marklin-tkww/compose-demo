# Jetpack Compose 开发指南
一种全新的Android UI框架，使用kotlin开发。</br>

### 优点
代码量更少，性能更高，加快开发速度，减少应用包的大小。
### 缺点
不太容易理解，轮子比较少，某些基础组件还不全，迁移难度较大，有bug。
### 声明式编程
相比于传统开发方式，不用获取节点，数据双向绑定，直接通过改变状态来更新UI。</br>
传统方式：像一棵树</br>
声明式编程：像搭积木</br>
数据渲染方式有点像DataBinding

# 开发准备
* Android Studio Preview [传送门](https://developer.android.com/studio/preview)
* Kotlin
* JDK
* 官方文档 [传送门](https://developer.android.com/jetpack/compose/mental-model)
### 配置
* gradle
```kotlin 
android {
    defaultConfig {
        ...
        minSdkVersion(21)
    }

    buildFeatures {
        // Enables Jetpack Compose for this module
        compose = true
    }
    ...

    // Set both the Java and Kotlin compilers to target Java 8.

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }

    composeOptions {
        kotlinCompilerVersion = "1.4.32"
        kotlinCompilerExtensionVersion = "$latest_version"
    }
}
```
* 依赖项
``` kotlin
dependencies {
    implementation("androidx.compose.ui:ui:$latest_version")
    // 工具支持，使用@Preview注解预览界面时导入
    implementation("androidx.compose.ui:ui-tooling:$latest_version")
    // 使用(Border, Background, Box, Image, Scroll, shapes, animations)时导入
    implementation("androidx.compose.foundation:foundation:$latest_version")
    // Material Design
    implementation("androidx.compose.material:material:$latest_version")
    // Material design icons
    implementation("androidx.compose.material:material-icons-core:$latest_version")
    implementation("androidx.compose.material:material-icons-extended:$latest_version")
    // 使用Livedata传递数据时导入
    implementation("androidx.compose.runtime:runtime-livedata:$latest_version")
    // 使用RxJava传递数据时导入  
    implementation("androidx.compose.runtime:runtime-rxjava2:$latest_version")
    // 使用ViewModel时导入
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$latest_version")
    // 使用Navigation时导入
    implementation("androidx.navigation:navigation-compose:2.4.0-alpha04")
    // 使用Coil加载图片时导入
    implementation("com.google.accompanist:accompanist-coil:0.13.0")
    // 使用Glide加载图片时导入
    implementation("com.google.accompanist:accompanist-glide:0.14.0")
    
    // UI 测试
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$latest_version")
}
```
# Hello World
```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ComponentActivity扩展方法，创建ComposeView，并设置ContentView为ComposeView,通过ComposeView渲染界面
        setContent {
            Text(text="Hello World")
        }
    }
}
# 总结
将Compose基本上所有的组件手撸了一遍之后，发现确实能够减少开发的代码量，因为少了各种xml，并且没有了原来那种手动写代码更新视图的繁琐流程，代码量预计少了40%，估计包的大小也能缩水一大半。

并且Compose自己实现了组件作用域的管理，基本上杜绝了原来的开发方式中诸多的内存泄漏问题，当然也可能是我没监听到，毕竟视图绘制方式和原来完全不一样了，官方貌似也没有提到过这一问题。

最让人惊讶的是Compose自带就支持了主题的无缝切换，相比于原来的切主题需要重启的方式，简直妙到家了。

当然目前Compose还存在许多不足的地方，如某些常用的组件还没有，迁移比较繁琐，还会偶发一些奇奇怪怪的编译bug等等。

让我们一起期待吧！
