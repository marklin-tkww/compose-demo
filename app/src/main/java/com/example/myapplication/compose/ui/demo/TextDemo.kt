package com.example.myapplication.compose.ui.demo

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.compose.titleLiveData

@ExperimentalTextApi
@Composable
fun TextPage() {
    TextContent()
}


@ExperimentalTextApi
@Composable
fun TextContent() {
    titleLiveData.value = "Compose Text Practice"
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            TextDemo()
        }
    }
}

@ExperimentalTextApi
@Composable
fun TextDemo() {
    Text(text = "Hello World")
    Spacer(modifier = Modifier.height(10.dp))
    Text(text = stringResource(id = R.string.text))
    Spacer(modifier = Modifier.height(10.dp))
    Text(text = "??????????????????", color = MaterialTheme.colors.primary)
    Spacer(modifier = Modifier.height(10.dp))
    Text(text = "???????????????", fontSize = 30.sp)
    Spacer(modifier = Modifier.height(10.dp))
    Text("??????", fontStyle = FontStyle.Italic)
    Spacer(modifier = Modifier.height(10.dp))
    Text(text = "??????", fontWeight = FontWeight.Bold)
    Spacer(modifier = Modifier.height(10.dp))
    Text(text = "???????????????", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Start)
    Spacer(modifier = Modifier.height(10.dp))
    Text(text = "??????????????????", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
    Spacer(modifier = Modifier.height(10.dp))
    Text(text = "???????????????", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.End)
    Spacer(modifier = Modifier.height(10.dp))
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(MaterialTheme.colors.primary)
    ) {
        Text(text = "?????????????????????", color = MaterialTheme.colors.onPrimary)
        Spacer(modifier = Modifier.height(10.dp))

    }

    Spacer(modifier = Modifier.height(10.dp))
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(MaterialTheme.colors.primary)
    ) {
        Text(text = "??????????????????", color = MaterialTheme.colors.onPrimary)
        Spacer(modifier = Modifier.height(10.dp))

    }

    Spacer(modifier = Modifier.height(10.dp))
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(MaterialTheme.colors.primary)
    ) {
        Text(text = "??????????????????", color = MaterialTheme.colors.onPrimary)
        Spacer(modifier = Modifier.height(10.dp))
    }

    Spacer(modifier = Modifier.height(10.dp))
    Text(text = "????????????", fontFamily = FontFamily.Serif)
    Text(
        text = "??????Assets????????????",
        fontFamily = FontFamily(Font(LocalContext.current.assets, "YouRan.ttf"))
    )

    Spacer(modifier = Modifier.height(10.dp))
    Text(text = "?????????????????????????????????????????????")

    Text(text = buildAnnotatedString {
        withStyle(style = SpanStyle(MaterialTheme.colors.primary)) {
            append("???????????????")
        }
        append("Hel ")
        withStyle(style = SpanStyle(MaterialTheme.colors.secondary)) {
            append("l")
        }
        append("o World")
        append("?????????")
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append("??????????????????")
        }
    })

    Text(text = "?????????????????????????????????")
    Spacer(modifier = Modifier.height(10.dp))

    Text(
        text = "??????2?????????2?????????2?????????2?????????2?????????2?????????2?????????2?????????2?????????2?????????2?????????2?????????2?????????2?????????2?????????2?????????2?????????2?????????2?????????2?????????2?????????2???",
        maxLines = 2
    )
    Spacer(modifier = Modifier.height(10.dp))

    Text(
        text = "??????2????????????????????????2????????????????????????2????????????????????????2????????????????????????2????????????????????????2????????????????????????2????????????????????????2????????????????????????2????????????????????????2????????????????????????2????????????????????????2??????????????????",
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        color = MaterialTheme.colors.secondaryVariant
    )

    Spacer(modifier = Modifier.height(10.dp))

    SelectionContainer {
        Text(text = "???????????????????????????")
    }

    Spacer(modifier = Modifier.height(10.dp))
    SelectionContainer {
        Column {
            Text(text = "???????????????????????????")
            Text(text = "?????????????????????????????????")
            DisableSelection {
                Text(text = "???????????????????????????")
            }
            Text(text = "???????????????????????????")
        }
    }

    Spacer(modifier = Modifier.height(10.dp))
    val context = LocalContext.current
    ClickableText(
        text = buildAnnotatedString { append("???????????????????????????") },
        style = TextStyle(color = MaterialTheme.colors.onBackground)
    ) {
        Toast.makeText(context, "???${it}????????????????????????", Toast.LENGTH_SHORT).show()
    }

    Spacer(modifier = Modifier.height(10.dp))

    val annotatedText = buildAnnotatedString {
        withStyle(
            SpanStyle(
                color = MaterialTheme.colors.onSurface
            )
        ) {
            append("?????????????????????, ??????  ")
        }

        pushStringAnnotation(
            tag = "URL",
            annotation = "https://developer.android.com"
        )

        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colors.primary,
                fontWeight = FontWeight.Bold
            )
        ) {
            append("??????")
        }
        pop()
    }

    ClickableText(text = annotatedText, onClick = { offset ->
        annotatedText.getStringAnnotations(
            tag = "URL", start = offset,
            end = offset
        ).firstOrNull()?.let { annotation ->
            Toast.makeText(context, "????????????${annotation.item}", Toast.LENGTH_SHORT).show()
        }
    })

    Spacer(modifier = Modifier.height(10.dp))
    var text1 by remember { mutableStateOf("????????????????????????") }
    TextField(value = text1, onValueChange = { text1 = it }, label = { Text("????????????????????????") })

    Spacer(modifier = Modifier.height(10.dp))
    var text2 by remember { mutableStateOf("????????????????????????") }
    OutlinedTextField(value = text2, onValueChange = { text2 = it }, label = { Text("????????????????????????") })

    Spacer(modifier = Modifier.height(10.dp))
    var text3 by remember { mutableStateOf("????????????????????????") }
    BasicTextField(
        value = text3,
        onValueChange = { text3 = it },
        textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
        modifier = Modifier.height(45.dp)
    )

    Spacer(modifier = Modifier.height(10.dp))
    var text4 by remember { mutableStateOf("???????????????") }
    TextField(
        value = text4, onValueChange = { text4 = it },
        label = { Text(text = "???????????????") },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )

    Spacer(modifier = Modifier.height(10.dp))
    var text5 by remember { mutableStateOf("") }
    BasicTextField(
        value = text5, onValueChange = { text5 = it },
        decorationBox = { innerTextField ->
            if (text5.isEmpty()) {
                Box(contentAlignment = Alignment.CenterStart) {
                    Text(text = "????????????????????????", color = MaterialTheme.colors.primary)
                }
            }
            innerTextField()
        },
        modifier = Modifier
            .background(Color.LightGray, CircleShape)
            .padding(5.dp, 20.dp)
            .fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(10.dp))
    var text6 by remember { mutableStateOf("??????????????????????????????") }
    BasicTextField(
        value = text6,
        onValueChange = { text6 = it },
        cursorBrush = SolidColor(Color.Green),
        modifier = Modifier.height(45.dp),
        textStyle = TextStyle(MaterialTheme.colors.onBackground)
    )


    Spacer(modifier = Modifier.height(100.dp))

}
