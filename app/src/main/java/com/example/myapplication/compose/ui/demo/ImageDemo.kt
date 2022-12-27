package com.example.myapplication.compose.ui.demo

import android.graphics.Paint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.applyCanvas
import androidx.core.graphics.createBitmap
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.myapplication.compose.titleLiveData


private const val imageUrl =
    "https://img.win3000.com/m00/55/8b/6bf1b0e0f4a0f47c7660e80a28363460_c_345_458.jpg"

@Composable
@Preview
fun ImagePage() {
    titleLiveData.value = "Compose Image Practice"
    ImageContent()
}

@Composable
fun ImageContent() {
    Box(modifier = Modifier.fillMaxWidth()) {
        ImageDemo()
    }
}

@Composable
fun ImageDemo() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(10.dp)
    ) {
        ObserveStateDemo()
        LoadDrawableDemo()
        LoadBitmapDemo()
        RoundCornerDemo()
        CircleDemo()
        ColorFilterDemo()
        TintDemo()
        ContentScaleDemo()

    }
}

@Composable
fun RoundCornerDemo() {
    Text(text = "加个20dp圆角")
    Surface(
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier.padding(10.dp)
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier.background(MaterialTheme.colors.onBackground)
        )
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun ColorFilterDemo() {
    Text(text = "colorFilter")
//    Image(
//        painter = rememberCoilPainter(
//            "https://macjpeg.macsc.com/macdown/pic/202009/03113634_e7f0bb805b.jpeg",
//            fadeIn = true
//        ),
//        contentScale = ContentScale.FillWidth,
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(300.dp),
//        colorFilter = ColorFilter.colorMatrix(
//            ColorMatrix(
//                floatArrayOf(
//                    -1f, 0f, 0f, 0f, 255f,
//                    0f, -1f, 0f, 0f, 255f,
//                    0f, 0f, -1f, 0f, 255f,
//                    0f, 0f, 0f, 1f, 0f
//                )
//            )
//        ),
//        contentDescription = null,
//    )
    AsyncImage(
        model = imageUrl, contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        colorFilter = ColorFilter.colorMatrix(
            ColorMatrix(
                floatArrayOf(
                    -1f, 0f, 0f, 0f, 255f,
                    0f, -1f, 0f, 0f, 255f,
                    0f, 0f, -1f, 0f, 255f,
                    0f, 0f, 0f, 1f, 0f
                )
            )
        )
    )
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun TintDemo() {
    Text(text = "tint")
    Image(
        painter = painterResource(id = com.example.myapplication.R.drawable.ic_launcher_background),
        contentDescription = null,
        colorFilter = ColorFilter.tint(androidx.compose.ui.graphics.Color.LightGray)
    )
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun ObserveStateDemo() {
    Text(text = "监听图片加载状态")
    Box {
        SubcomposeAsyncImage(
            model = imageUrl,
            contentDescription = "哈哈哈哈"
        ) {
            val state = painter.state
            println("mark----state = $state")
            if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                CircularProgressIndicator()
            } else {
                SubcomposeAsyncImageContent()
            }
        }

    }
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun LoadBitmapDemo() {
    Text(text = "加载bitmap")
    val bitmap = createBitmap(100, 100)
    bitmap.applyCanvas {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = MaterialTheme.colors.onBackground.toArgb()
        drawCircle(50f, 50f, 50f, paint)
    }
    Image(bitmap = bitmap.asImageBitmap(), contentDescription = null)
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun LoadDrawableDemo() {
    Text(text = "加载drawable")
    Image(
        painter = painterResource(id = com.example.myapplication.R.drawable.close_black),
        contentDescription = null
    )
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun CircleDemo() {
    Text(text = "圆形头像")
    Surface(
        shape = RoundedCornerShape(percent = 50),
        modifier = Modifier.padding(10.dp)
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .background(MaterialTheme.colors.onBackground),
            contentScale = ContentScale.Crop
        )
    }
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun LoadNetworkUrlImage(contentScale: ContentScale?) {
    AsyncImage(
        model = imageUrl,
        contentDescription = null,
        modifier = Modifier
            .size(200.dp, 300.dp)
            .background(MaterialTheme.colors.onBackground),
        contentScale = contentScale ?: ContentScale.Fit
    )
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun ContentScaleDemo() {
    Text(text = "ContentScale.Crop")
    LoadNetworkUrlImage(contentScale = ContentScale.Crop)

    Text(text = "ContentScale.Fit")
    LoadNetworkUrlImage(contentScale = ContentScale.Fit)

    Text(text = "ContentScale.FillBounds")
    LoadNetworkUrlImage(contentScale = ContentScale.FillBounds)

    Text(text = "ContentScale.FillHeight")
    LoadNetworkUrlImage(contentScale = ContentScale.FillHeight)

    Text(text = "ContentScale.FillWidth")
    LoadNetworkUrlImage(contentScale = ContentScale.FillWidth)

    Text(text = "ContentScale.Inside")
    LoadNetworkUrlImage(contentScale = ContentScale.Inside)

    Text(text = "ContentScale.None")
    LoadNetworkUrlImage(contentScale = ContentScale.None)
}