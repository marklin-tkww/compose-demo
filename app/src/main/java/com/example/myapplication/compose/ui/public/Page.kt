package com.example.myapplication.compose.ui.public

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.myapplication.compose.ui.theme.CustomThemeManager
import com.example.myapplication.compose.ui.theme.ThemeType


@Composable
fun Page(
    title: String,
    themeType: ThemeType,
    isDarkTheme: Boolean,
    leftIcon: ImageVector? = null,
    rightIcon: ImageVector? = null,
    onLeftClick: () -> Unit = {},
    onRightClick: () -> Unit = {},
    content: @Composable () -> Unit
) {
    CustomThemeManager.WithTheme(themeType, isDarkTheme) {
        Surface(color = MaterialTheme.colors.background) {
            Column {
                AppBar(title = title, leftIcon, rightIcon, onLeftClick, onRightClick)
                content.invoke()
            }

        }
    }
}
