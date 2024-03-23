package com.example.restaurantfindkun.screen.result

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.restaurantfindkun.data.api.response.Shop
import com.example.restaurantfindkun.screen.component.CardContent
import com.example.restaurantfindkun.screen.top.TopViewModel

@Composable
fun ResultScreen(
    viewModel: ResultViewModel = hiltViewModel()
) {
    val listApi by viewModel.apiList.collectAsStateWithLifecycle()

    viewModel.executeReposLoad()
    ListSet(list = listApi)
}

@Composable
fun ListSet(
    list: List<Shop>
) {
    LazyColumn(
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(top = 70.dp)
    ) {
        list.forEach { api ->
            item {
                CardContent(
                    imageLink = api.logoImage!!,
                    storeName = api.name!!,
                    storePosition = api.smallArea!!.name!!,
                    storeCatch = api.catchText!!)
            }
        }
    }
}