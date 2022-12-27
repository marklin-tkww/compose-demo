package com.example.myapplication.compose.ui.demo

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.myapplication.compose.titleLiveData
import kotlin.math.roundToInt

@Composable
fun GesturePage() {
    titleLiveData.value = "Compose Gesture Practice"
    GestureContent()
}

@Composable
fun GestureContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        GestureDemo()
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GestureDemo() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        ClickableDemo()
        DoubleClickableDemo()
        SwipeAbleDemo()
        TouchPositionDemo()
        HorizontalDragDemo()
        VerticalDragDemo()
        DragDemo()
        TransformableDemo()
    }
}

@Composable
fun ClickableDemo() {
    Spacer(modifier = Modifier.height(20.dp))
    Text(
        "--------按钮点击事件监听----------",
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
    var clickCount by remember { mutableStateOf(0) }
    Button(onClick = { clickCount++ }, modifier = Modifier.offset(100.dp)) {
        Text("Clicked $clickCount")
    }
}

@Composable
fun DoubleClickableDemo() {
    Spacer(modifier = Modifier.height(20.dp))
    Text(
        "------单击，双击，长按，按下事件监听-----",
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .size(100.dp)
            .offset(100.dp)
            .background(MaterialTheme.colors.primary)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        Toast
                            .makeText(context, "按下了按钮", Toast.LENGTH_SHORT)
                            .show()
                    },
                    onDoubleTap = {
                        Toast
                            .makeText(context, "双击下了按钮", Toast.LENGTH_SHORT)
                            .show()
                    },
                    onLongPress = {
                        Toast
                            .makeText(context, "长按下了按钮", Toast.LENGTH_SHORT)
                            .show()
                    },
                    onTap = {
                        Toast
                            .makeText(context, "单击下了按钮", Toast.LENGTH_SHORT)
                            .show()
                    },
                )
            }, contentAlignment = Alignment.Center
    ) {
        Text(
            "Clicked me",
            color = MaterialTheme.colors.onSurface,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxHeight()
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun SwipeAbleDemo() {
    Spacer(modifier = Modifier.height(20.dp))
    Text("--------SwipeAble可滑动滑块的---- ", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
    val width = 96.dp
    val squareSize = 48.dp

    val swipeAbleState = rememberSwipeableState(0)
    val sizePx = with(LocalDensity.current) { squareSize.toPx() }
    val anchors = mapOf(0f to 0, sizePx to 1) // Maps anchor points (in px) to states

    Box(modifier = Modifier.offset(100.dp)) {
        Box(
            modifier = Modifier
                .width(width)
                .swipeable(
                    state = swipeAbleState,
                    anchors = anchors,
                    thresholds = { _, _ -> FractionalThreshold(0.3f) },
                    orientation = Orientation.Horizontal
                )
                .background(MaterialTheme.colors.secondary)
        ) {
            Box(
                Modifier
                    .offset { IntOffset(swipeAbleState.offset.value.roundToInt(), 0) }
                    .size(squareSize)
                    .background(MaterialTheme.colors.onBackground)
            )
        }
    }

}


@Composable
fun TouchPositionDemo() {
    Spacer(modifier = Modifier.height(20.dp))
    var touchedX by remember { mutableStateOf(0f) }
    var touchedY by remember { mutableStateOf(0f) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    touchedX = change.position.x
                    touchedY = change.position.y
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(text = "------这是一个监听触摸位置的组件------")
            Text(text = "touchedX=${touchedX.toInt()}   touchedY=${touchedY.toInt()}")
        }
    }
}

@Composable
fun HorizontalDragDemo() {
    Spacer(modifier = Modifier.height(20.dp))
    Text("--------水平拖动--------", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
    var offsetX by remember { mutableStateOf(0f) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(modifier = Modifier
            .size(100.dp)
            .offset { IntOffset(offsetX.roundToInt(), 0) }
            .background(MaterialTheme.colors.primary)
            .draggable(
                orientation = Orientation.Horizontal,
                state = rememberDraggableState { delta ->
                    offsetX += delta
                }
            )
        )
    }

    Spacer(modifier = Modifier.height(50.dp))
}

@Composable
fun VerticalDragDemo() {
    Spacer(modifier = Modifier.height(20.dp))
    Text("--------垂直拖动--------", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
    var offsetY by remember { mutableStateOf(0f) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(modifier = Modifier
            .size(100.dp)
            .offset { IntOffset(0, offsetY.roundToInt()) }
            .background(MaterialTheme.colors.primary)
            .draggable(
                orientation = Orientation.Vertical,
                state = rememberDraggableState { delta ->
                    offsetY += delta
                }
            )
        )
    }

    Spacer(modifier = Modifier.height(50.dp))
}

@Composable
fun DragDemo() {
    Spacer(modifier = Modifier.height(50.dp))
    Text("-------------任意拖动-----", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                .background(MaterialTheme.colors.primary)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        offsetX += dragAmount.x
                        offsetY += dragAmount.y
                    }
                }
        )
    }
    Spacer(modifier = Modifier.height(50.dp))
}

@Composable
fun TransformableDemo() {
    Spacer(modifier = Modifier.height(50.dp))
    Text(
        "-------------缩放、平移、旋转-----",
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
    var scale by remember { mutableStateOf(1f) }
    var roration by remember { mutableStateOf(0f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    rotationZ = roration,
                    translationX = offset.x,
                    translationY = offset.y
                )
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, zoom, rotationChanged ->
                        scale *= zoom
                        roration += rotationChanged
                        offset += pan
                    }
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        offset = Offset(dragAmount.y, dragAmount.y)
                    }
                }
                .background(MaterialTheme.colors.primary)
                .size(100.dp)
        )
    }
    Spacer(modifier = Modifier.height(50.dp))
}

