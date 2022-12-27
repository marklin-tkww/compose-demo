package com.example.myapplication.compose.ui.demo

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.myapplication.R
import com.example.myapplication.compose.titleLiveData

@Composable
fun CanvasPage() {
    titleLiveData.value = "Compose Canvas Practice"
    CanvasContent()
}

@Composable
fun CanvasContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        ComposeDemo()
    }
}

@Composable
fun ComposeDemo() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        val color = MaterialTheme.colors.onBackground
        CanvasLineDemo(color)
        CanvasDashLineDemo(color)
        CanvasRoundLineDemo(color)
        CanvasRectDemo(color)
        CanvasCircleDemo(color)
        CanvasPathDemo(color)
        CanvasArcDemo(color)
        CanvasSectorDemo(color)
        CanvasOvalDemo(color)
        CanvasRoundRectDemo(color)
        CanvasCurveRectDemo(color)
        CanvasRotateRectDemo(color)
        CanvasRotateAndTransitionRectDemo(color)
        CanvasBitmapDemo(color)
        AndroidCanvasDemo(color)
    }
}

@Composable
fun CanvasLineDemo(color: Color) {
    Text(
        text = "-----------画了一根实线---------------",
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
    androidx.compose.foundation.Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp, 0.dp)
            .height(30.dp)
    ) {
        drawLine(color, Offset(0f, 20f), Offset(800f, 20f), strokeWidth = 5f)
    }
    Spacer(modifier = Modifier.height(10.dp))
}


@Composable
fun CanvasDashLineDemo(color: Color) {
    Text(
        text = "-----------画了一根虚线---------------",
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
    androidx.compose.foundation.Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp, 0.dp)
            .height(30.dp)
    ) {
        drawLine(
            color, Offset(0f, 20f), Offset(800f, 20f), strokeWidth = 5f,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 20f), 5f)
        )
    }
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun CanvasRoundLineDemo(color: Color) {
    Text(
        text = "-----------左右端点是圆滑的---------------",
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
    androidx.compose.foundation.Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
            .padding(30.dp, 0.dp)
    ) {
        drawLine(
            color, Offset(0f, 20f), Offset(800f, 20f), strokeWidth = 15f,
            cap = StrokeCap.Round
        )
    }
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun CanvasRectDemo(color: Color) {
    Text(
        text = "-----------画了一个矩形---------------",
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
    androidx.compose.foundation.Canvas(
        modifier = Modifier
            .size(100.dp)
            .offset(150.dp)
    ) {
        drawRect(color, size = size / 2f)
    }
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun CanvasCircleDemo(color: Color) {
    Text(
        text = "-----------画了一个圆形---------------",
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
    androidx.compose.foundation.Canvas(
        modifier = Modifier
            .size(100.dp)
            .offset(120.dp)
    ) {
        drawCircle(color)
    }
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun CanvasPathDemo(color: Color) {
    Text(
        text = "-----------画了一个随意图形Path---------------",
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )

    androidx.compose.foundation.Canvas(
        modifier = Modifier
            .size(100.dp)
            .offset(120.dp)
    ) {
        val path = Path().apply {
            lineTo(100f, 0f)
            lineTo(200f, 40f)
            lineTo(380f, 20f)
            lineTo(480f, 100f)
            lineTo(500f, 180f)
        }
        drawPath(path, color, style = Stroke(5f))
    }
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun CanvasArcDemo(color: Color) {
    Text(
        text = "-----------画了一个圆弧---------------",
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
    androidx.compose.foundation.Canvas(
        modifier = Modifier
            .size(100.dp)
            .offset(100.dp)
    ) {
        drawArc(color, 0f, -135f, false, style = Stroke(5f))
    }
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun CanvasSectorDemo(color: Color) {
    Text(
        text = "-----------画了一个扇形---------------",
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
    androidx.compose.foundation.Canvas(
        modifier = Modifier
            .size(100.dp)
            .offset(100.dp)
    ) {
        drawArc(color, 0f, -135f, true, style = Stroke(5f))
    }
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun CanvasOvalDemo(color: Color) {
    Text(
        text = "-----------画了一个椭圆---------------",
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
    androidx.compose.foundation.Canvas(
        modifier = Modifier
            .size(120.dp, 100.dp)
            .offset(100.dp)
    ) {
        drawOval(color)
    }
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun CanvasRoundRectDemo(color: Color) {
    Text(
        text = "-----------画了一个圆角矩形---------------",
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
    androidx.compose.foundation.Canvas(
        modifier = Modifier
            .size(120.dp, 100.dp)
            .offset(100.dp)
    ) {
        drawRoundRect(color, cornerRadius = CornerRadius(50f))
    }
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun CanvasCurveRectDemo(color: Color) {
    Text(
        text = "-----------画了一个贝塞尔曲线---------------",
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
    androidx.compose.foundation.Canvas(
        modifier = Modifier
            .size(120.dp, 100.dp)
            .offset(100.dp)
    ) {
        val path = Path().apply {
            cubicTo(0f, 100f, 100f, 0f, 200f, 100f)
        }
        drawPath(path, color, style = Stroke(10f))
    }
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun CanvasRotateRectDemo(color: Color) {
    Text(
        text = "-----------画了一个旋转了45度的矩形---------------",
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
    androidx.compose.foundation.Canvas(
        modifier = Modifier
            .size(120.dp, 100.dp)
            .offset(100.dp)
    ) {
        rotate(45f) {
            drawRect(
                color, topLeft = Offset(100f, 100f),
                size = size / 2f
            )
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun CanvasRotateAndTransitionRectDemo(color: Color) {
    Text(
        text = "-----------画了一个旋转了45度并且位移了300的矩形---------------",
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
    androidx.compose.foundation.Canvas(
        modifier = Modifier
            .size(120.dp)
            .offset(100.dp)
    ) {
        withTransform({
            translate(300f)
            rotate(45f)
        }) {
            drawRect(
                color, topLeft = Offset(0f, 100f),
                size = size / 2f
            )

        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun CanvasBitmapDemo(color: Color) {
    Text(
        text = "-----------画了一个用bitmap组成的图形---------------",
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
    val context = LocalContext.current
    androidx.compose.foundation.Canvas(
        modifier = Modifier
            .size(120.dp)
            .offset(150.dp)
    ) {
//    val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.close_black) 报错
        val bitmap = getBitmapFromImage(context, R.drawable.close_black)
        drawImage(bitmap.asImageBitmap())
    }
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun AndroidCanvasDemo(color: Color) {
    Text(
        "-----------画了一个AndroidCanvas---------------",
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
    androidx.compose.foundation.Canvas(
        modifier = Modifier
            .size(100.dp)
            .offset(150.dp)
    ) {
        drawIntoCanvas { canvas ->
            val paint = Paint()
            paint.color = color
            canvas.drawOval(0f, 0f, 100f, 100f, paint)
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}


private fun getBitmapFromImage(context: Context, drawable: Int): Bitmap {
    val db = ContextCompat.getDrawable(context, drawable)
    val bit = Bitmap.createBitmap(
        db!!.intrinsicWidth, db.intrinsicHeight, Bitmap.Config.ARGB_8888
    )
    val canvas = android.graphics.Canvas(bit)
    db.setBounds(0, 0, canvas.width, canvas.height)
    db.draw(canvas)
    return bit
}