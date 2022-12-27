package com.example.myapplication.composeAndview.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import com.example.myapplication.composeAndview.viewmodel.ComposeUiViewModel

@Composable
fun ComposeUiPage(viewModel: ComposeUiViewModel) {
    val number by viewModel.number.observeAsState(0)
    val number1 by viewModel.number1.observeAsState(0)
    val responseMessage by viewModel.responseMessage.observeAsState("")

    Column {
        ComposeUiContent(
            number,
            numberChange = { viewModel.updateNumber(number) },
            number1,
            number1Change = { viewModel.updateNumber1(number1) })

        TestRequestBaiDu(responseMessage, testRequestBaiDu = { viewModel.testRequestBaiDu() })
    }
}


@Composable
fun ComposeUiContent(
    number: Int,
    numberChange: (Int) -> Unit,
    number1: Int,
    number1Change: (Int) -> Unit
) {
    Column {
        Button(onClick = { numberChange.invoke(number) }) {
            Text(text = "第一组数字是 :$number")
        }

        Button(onClick = { number1Change.invoke(number1) }) {
            Text(text = "第二组数字是 :$number1")
        }
    }
}


@Composable
fun TestRequestBaiDu(responseMessage: String, testRequestBaiDu: () -> Unit) {
    Column {
        Button(onClick = { testRequestBaiDu.invoke() }) {
            Text(text = "测试请求百度地址")
        }

        Text(text = "API返回数据的结果是：$responseMessage")
    }

}
