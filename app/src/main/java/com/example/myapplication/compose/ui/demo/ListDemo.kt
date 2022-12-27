package com.example.myapplication.compose.ui.demo

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.myapplication.compose.navigation.Screen
import com.example.myapplication.compose.titleLiveData
import com.example.myapplication.compose.ui.demo.viewmodel.ListViewModel
import com.example.myapplication.compose.ui.public.FunctionList
import com.example.myapplication.compose.ui.public.HorizontalNoMoreItem
import com.example.myapplication.compose.ui.public.VerticalNoMoreItem
import com.example.myapplication.compose.viewModel.ComposeViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListPage() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.List.main) {
        composable(Screen.List.main) { ListContent(navController) }
        composable(Screen.List.scrollable_row) { ScrollableRowDemo() }
        composable(Screen.List.scrollable_column) { ScrollableColumnDemo() }
        composable(Screen.List.lazy_row) { LazyRowDemo() }
        composable(Screen.List.lazy_column) { LazyColumnDemo() }
        composable(Screen.List.sticky_header) { StickerHeaderDemo() }
        composable(Screen.List.lazy_vertical_grid) { LazyVerticalGridDemo() }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListContent(navController: NavHostController) {
    titleLiveData.value = "Compose List Practice"
    val viewModel: ListViewModel = viewModel()
    FunctionList(functions = viewModel.functions, navController = navController)
}

@Composable
fun ScrollableRowDemo() {
    titleLiveData.value = "Compose Scrollable Row Practice"
    val viewModel: ComposeViewModel = viewModel()
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
        ) {
            for (image in viewModel.images) {
                ImageCard(image, modifier = Modifier.size(200.dp, 160.dp), "这是ScrollableRow")
            }
        }
    }

}

@Composable
fun ScrollableColumnDemo() {
    titleLiveData.value = "Compose Scrollable Column Practice"
    val viewModel: ComposeViewModel = viewModel()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        for (image in viewModel.images) {
            ImageCard(
                image, modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp), "这是ScrollableColumn"
            )
        }
    }
}


@Composable
fun LazyRowDemo() {
    titleLiveData.value = "Compose Lazy Row Practice"
    val viewModel: ComposeViewModel = viewModel()
    LazyRow(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) // 间距
    {
        items(viewModel.images, key = {
            it
        }) { image ->
            ImageCard(
                image = image,
                modifier = Modifier.size(200.dp, 160.dp),
                text = "这是 LazyRowDemo"
            )

        }
        item {
            HorizontalNoMoreItem()
        }
    }
}

@Composable
fun LazyColumnDemo() {
    titleLiveData.value = "Compose Lazy Column Practice"
    val viewModel: ComposeViewModel = viewModel()
    LazyColumn(verticalArrangement = Arrangement.spacedBy(15.dp)) {
        item {}
        items(viewModel.images, key = {
            it
        }) { image ->
            ImageCard(
                image, modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp), "这是LazyColumn"
            )
        }
        item {
            VerticalNoMoreItem()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StickerHeaderDemo() {
    titleLiveData.value = "Compose Sticker Header Practice"
    val viewModel: ComposeViewModel = viewModel()
    LazyColumn(verticalArrangement = Arrangement.spacedBy(15.dp)) {
        item {
            ImageCard(
                image = viewModel.images[0],
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                text = "这是lazyColum"
            )
        }

        stickyHeader {
            Column(
                modifier = Modifier
                    .height(50.dp)
                    .background(MaterialTheme.colors.background),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "我是粘性标题",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

            }
        }

        items(viewModel.images, key = {
            it
        }) { image ->
            ImageCard(
                image, modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp), "这是LazyColumn"
            )
        }
        item {
            VerticalNoMoreItem()
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun LazyVerticalGridDemo() {
    titleLiveData.value = "Compose LazyVerticalGrid Practice"
    val viewModel: ComposeViewModel = viewModel()
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Adaptive(minSize = 128.dp) // 最小宽度
    ) {
        items(viewModel.images + viewModel.images + viewModel.images) { image ->
            ImageCard(image = image, modifier = Modifier.size(128.dp), text = "这是LazyVerticalGrid")
        }
    }
}


@Composable
fun ImageCard(image: String, modifier: Modifier, text: String) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        AsyncImage(
            model = image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray),
            contentScale = ContentScale.Crop
        )
//        Image(
//            painter = rememberCoilPainter(request = image, fadeIn = true),
//            contentDescription = null,
//            modifier = Modifier
//                .fillMaxSize()
//                .background(Color.LightGray),
//            contentScale = ContentScale.Crop
//        )
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = text)
        }
    }
}