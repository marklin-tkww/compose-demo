package com.example.myapplication.compose.ui.demo

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.myapplication.compose.titleLiveData

@Composable
fun AnimationPage() {
    titleLiveData.value = "Compose Animation Practice"
    AnimationContent()
}

@Composable
fun AnimationContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        AnimationDemo()
    }
}

@Composable
fun AnimationDemo() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        HideLevelAnimation()

        LowLevelAnimation()
    }
}

@Composable
fun LowLevelAnimation() {
    Spacer(modifier = Modifier.height(20.dp))
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
        Text("------------低级别动画API------------")
    }

    AnimateFloatAsStateDemo()

    AnimatableDemo()

    UpdateTransitionDemo()

    RememberInfiniteTransitionDemo()

    SpringDemo()

    TweenDemo()
}

@Composable
fun AnimateFloatAsStateDemo() {
    Spacer(modifier = Modifier.height(10.dp))
    Text("-------animateFloatAsState:给定初始值和结束值，展示动画------")
    var enabled by remember { mutableStateOf(false) }
    val scale: Float by animateFloatAsState(if (enabled) 1f else 0.5f)
    Row {
        Button(onClick = { enabled = !enabled }) {
            Text(text = "animateFloatAsState")
        }
    }

    Text(text = "图形的scaleX = $scale  图形的scaleY = $scale")

    Box(
        modifier = Modifier
            .size(100.dp)
            .graphicsLayer(scaleX = scale, scaleY = scale)
            .background(MaterialTheme.colors.primary)
    )
}

@Composable
fun AnimatableDemo() {
    Spacer(modifier = Modifier.height(10.dp))
    Text("------Animatable:给定初始值，使用animateTo(targetValue)过渡到结束值----")
    var enabledAnimatable by remember { mutableStateOf(false) }
    val translationX = remember {
        Animatable(0f)
    }
    LaunchedEffect(enabledAnimatable) {
        if (enabledAnimatable) translationX.animateTo(100f) else translationX.animateTo(0f)
    }

    Row {
        Button(onClick = { enabledAnimatable = !enabledAnimatable }) {
            Text(text = "Animatable")
        }
    }

    Text(text = "图形的offset = ${translationX.value}")

    Spacer(modifier = Modifier.height(10.dp))
    Box(
        Modifier
            .size(100.dp)
            .offset(translationX.value.dp, 0.dp)
            .background(MaterialTheme.colors.primary)
    )
}

@Composable
fun UpdateTransitionDemo() {
    Spacer(modifier = Modifier.height(10.dp))
    Text("----updateTransition:组合多种动画-----")
    var state by remember { mutableStateOf(false) }
    val transition = updateTransition(state, label = "")
    val color by transition.animateColor(label = "") {
        if (it) Color.Red else MaterialTheme.colors.primary
    }
    val offset by transition.animateIntOffset(label = "") {
        if (it) IntOffset(100, 100) else IntOffset(0, 0)
    }
    Row {
        Button(onClick = { state = !state }) {
            Text("updateTransition")
        }
        Text(
            "color = ${
                color.value.toString(16).subSequence(0, 8)
            }   \noffsetX = ${offset.x}  offsetY = ${offset.y}"
        )
    }
    Box(
        Modifier
            .size(100.dp)
            .offset { offset }
            .background(color)
    )
}

@Composable
fun RememberInfiniteTransitionDemo() {
    Spacer(modifier = Modifier.height(10.dp))
    Text(text = "----rememberInfiniteTransition用于一直执行动画-----")
    val infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Green,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    val offset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 100f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        Modifier
            .size(100.dp)
            .offset(offset.dp, 0.dp)
            .background(color)
    )
}

@Composable
fun HideLevelAnimation() {
    Spacer(modifier = Modifier.height(20.dp))
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
        Text("-------------------高级别动画API-------------")
    }
    AnimatedVisibilityDemo()
    AnimateContentSizeDemo()
    CrossFadeDemo()
}

@Composable
fun AnimatedVisibilityDemo() {
    var visible by remember { mutableStateOf(false) }
    Spacer(modifier = Modifier.height(10.dp))
    Text("-----------AnimatedVisibility:控制显示与隐藏-------")
    Row {
        Button(onClick = { visible = !visible }) {
            Text(text = "AnimatedVisibility")
        }
        Text(text = "下面图形的isVisible = $visible", modifier = Modifier.padding(10.dp))
    }

    AnimatedVisibility(
        visible = visible,
        modifier = Modifier.padding(10.dp)
    ) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colors.primary)
                .size(100.dp)
        )
    }
}

@Composable
fun AnimateContentSizeDemo() {
    var size by remember {
        mutableStateOf(100.dp)
    }
    Spacer(modifier = Modifier.height(10.dp))
    Text("-----------AnimateContentSize:尺寸变化时添加动画过渡--------")

    Row {
        Button(onClick = { size = if (size == 100.dp) 150.dp else 100.dp }) {
            Text("AnimateContentSize")
        }
        Text(text = "图形的size = $size", modifier = Modifier.padding(10.dp))
    }
    Spacer(modifier = Modifier.height(10.dp))
    Box(
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .animateContentSize()
            .padding(10.dp)
            .size(size)
    )
}

@Composable
fun CrossFadeDemo() {
    var currentPage by remember { mutableStateOf("1") }
    Spacer(modifier = Modifier.height(10.dp))
    Text("---------CrossFade:多个页面切换时添加过渡效果---------")
    Row {
        Button(onClick = { currentPage = if (currentPage == "1") "2" else "1" }) {
            Text("CrossFade")
        }
        Text("currentPage = $currentPage", modifier = Modifier.padding(10.dp))
    }
    Spacer(modifier = Modifier.height(10.dp))

    Crossfade(targetState = currentPage) {
        when (it) {
            "1" -> {
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colors.onBackground)
                        .padding(10.dp)
                        .size(100.dp)
                )
            }
            "2" -> {
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colors.primary)
                        .padding(10.dp)
                        .size(100.dp)
                )
            }
        }

    }
}

@Composable
fun SpringDemo() {
    Spacer(modifier = Modifier.height(60.dp))
    var enabled by remember { mutableStateOf(false) }
    var dampingRatio by remember { mutableStateOf(Spring.DampingRatioNoBouncy) }
    var stiffness by remember { mutableStateOf(Spring.StiffnessVeryLow) }
    val offsetX: Int by animateIntAsState(
        if (enabled) 200 else 0, animationSpec = spring(dampingRatio, stiffness)
    )
    Row {
        Button(onClick = { enabled = !enabled }) {
            Text("Animate with spring")
        }
        Text("offsetX=${offsetX}")
    }
    Spacer(modifier = Modifier.height(20.dp))
    Box(
        Modifier
            .size(10.dp)
            .offset(offsetX.dp, 0.dp)
            .background(MaterialTheme.colors.primary)
    )
    Spacer(modifier = Modifier.height(20.dp))
    OutlinedTextField(
        value = dampingRatio.toString(),
        onValueChange = { dampingRatio = if (it.isNotEmpty()) it.toFloat() else 0.2f },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        label = { Text("dampingRatio 弹性系数") })
    OutlinedTextField(
        value = stiffness.toString(),
        onValueChange = { stiffness = if (it.isNotEmpty()) it.toFloat() else 0.2f },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        label = { Text("stiffness 移动速度") })
    Spacer(modifier = Modifier.height(10.dp))
}


@Composable
fun TweenDemo() {
    Spacer(modifier = Modifier.height(60.dp))
    var enabled by remember { mutableStateOf(false) }
    var duration by remember { mutableStateOf(300) }
    var easingType by remember { mutableStateOf(LinearEasing) }
    val offsetX: Int by animateIntAsState(
        if (enabled) 200 else 0,
        animationSpec = tween(durationMillis = duration, easing = easingType)
    )
    Row {
        Button(onClick = { enabled = !enabled }) {
            Text("Animate with tween")
        }
        Text("offsetX=${offsetX}")
    }
    Spacer(modifier = Modifier.height(20.dp))
    Box(
        Modifier
            .size(10.dp)
            .offset(offsetX.dp, 0.dp)
            .background(MaterialTheme.colors.primary)
    )
    Spacer(modifier = Modifier.height(20.dp))
    RadioButton(
        isSelected = easingType == LinearEasing,
        text = "LinearEasing"
    ) {
        easingType = LinearEasing
    }
    RadioButton(
        isSelected = easingType == FastOutSlowInEasing,
        text = "FastOutSlowInEasing"
    ) {
        easingType = FastOutSlowInEasing
    }
    RadioButton(
        isSelected = easingType == LinearOutSlowInEasing,
        text = "LinearOutSlowInEasing"
    ) {
        easingType = LinearOutSlowInEasing
    }
    RadioButton(
        isSelected = easingType == FastOutLinearInEasing,
        text = "FastOutLinearInEasing"
    ) {
        easingType = FastOutLinearInEasing
    }
    val cubicBezierEasing = CubicBezierEasing(0f, 0.2f, 0.8f, 1f)
    RadioButton(
        isSelected = easingType == cubicBezierEasing,
        text = "CubicBezierEasing(自定义）"
    ) {
        easingType = cubicBezierEasing
    }
    OutlinedTextField(
        value = duration.toString(),
        onValueChange = { duration = if (it.isNotEmpty()) it.toInt() else 300 },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        label = { Text("duration 动画时间") })
    Spacer(modifier = Modifier.height(10.dp))
}