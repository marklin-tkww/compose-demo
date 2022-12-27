package com.example.myapplication.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.compose.navigation.Screen
import com.example.myapplication.compose.ui.demo.*
import com.example.myapplication.compose.ui.public.FunctionList
import com.example.myapplication.compose.ui.public.Page
import com.example.myapplication.compose.ui.theme.CustomThemeManager
import com.example.myapplication.compose.ui.theme.ThemeType
import com.example.myapplication.compose.viewModel.ComposeViewModel

val titleLiveData = MutableLiveData<String>()
val themeTypeState = mutableStateOf(ThemeType.Default)
val darkThemeState = mutableStateOf(false)

class ComposeActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val rememberTitle: String by titleLiveData.observeAsState("Compose Practice  Demo")
            val themType: ThemeType by themeTypeState
            val isDarkTheme: Boolean by darkThemeState
            val wrappedColor = CustomThemeManager.getWrappedColor(themType)
            window.statusBarColor = if (isDarkTheme) {
                Color(0xFF181818)
            } else {
                wrappedColor.lightColors.primary
            }.toArgb()

            Page(title = rememberTitle, themeType = themType, isDarkTheme = isDarkTheme) {
                Content()
            }
        }
    }
}


@OptIn(ExperimentalUnitApi::class, ExperimentalTextApi::class)
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun Content() {
    val navControl = rememberNavController()
    NavHost(navController = navControl, startDestination = Screen.main) {
        composable(Screen.main) { Main(navControl = navControl) }
        composable(Screen.layout) { LayoutPage() }
        composable(
            Screen.animation,
        ) { AnimationPage() }
        composable(Screen.gesture) { GesturePage() }
        composable(Screen.image) { ImagePage() }
        composable(Screen.theme) { ThemePage() }
        composable(Screen.canvas) { CanvasPage() }
        composable(Screen.custom_layout) { CustomLayoutPage() }
        composable(Screen.List.main) { ListPage() }
        composable(Screen.text) { TextPage() }
    }
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun Main(navControl: NavHostController) {
    titleLiveData.value = "Compose Practice Demo"
    val viewModel: ComposeViewModel = viewModel()
    FunctionList(functions = viewModel.functions, navControl)
}


