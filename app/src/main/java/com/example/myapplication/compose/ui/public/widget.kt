package com.example.myapplication.compose.ui.public

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.compose.model.FuncInfo


@ExperimentalMaterialApi
@Composable
fun FunctionList(functions: ArrayList<FuncInfo>, navController: NavHostController) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(15.dp)) {
        item {
            Spacer(modifier = Modifier.size(0.dp))
        }

        items(functions) { func ->
            FuncItem(func, navController)
        }

        item {
            Spacer(Modifier.size(0.dp))
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun FuncItem(func: FuncInfo, navController: NavHostController) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
        .padding(15.dp, 0.dp),
        backgroundColor = func.color,
        elevation = 2.dp,
        onClick = {
            navController.navigate(func.path)
        }
    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = func.name,
                textAlign = TextAlign.Center,
                fontSize = MaterialTheme.typography.h5.fontSize,
                color = Color.White
            )

        }
    }
}

@Composable
fun VerticalNoMoreItem() {
    Text(
        text = "--没有更多了--",
        color = Color.DarkGray,
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 20.dp),
        textAlign = TextAlign.Center
    )
}

@Composable
fun HorizontalNoMoreItem() {
    Column(
        modifier = Modifier
            .height(160.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "---没有更多了----",
            color = Color.DarkGray,
            modifier = Modifier
                .padding(0.dp, 0.dp, 20.dp, 0.dp),
            textAlign = TextAlign.Center
        )
    }
}