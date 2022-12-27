package com.example.myapplication.compose.ui.public

import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.R

@Composable
fun AppBar(
    title: String,
    leftIcon: ImageVector? = null,
    rightIcon: ImageVector? = null,
    onLeftClick: () -> Unit = {},
    onRightClick: () -> Unit = {}
) {
    TopAppBar {
        leftIcon?.let {
            Icon(it, contentDescription = null, Modifier.clickable {
                onLeftClick.invoke()
            })
        }

        Text(text = title, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)

        rightIcon?.let {
            Icon(it, contentDescription = null, Modifier.clickable {
                onRightClick.invoke()
            })
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewAppbar() {
    AppBar(title = "hello word", leftIcon = ImageVector.vectorResource(id = R.drawable.close_black))
}