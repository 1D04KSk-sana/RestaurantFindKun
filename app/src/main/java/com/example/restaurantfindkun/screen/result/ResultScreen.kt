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
import com.example.restaurantfindkun.screen.component.CompanionObject

//
//検索結果画面
//
@Composable
fun ResultScreen(
    viewModel: ResultViewModel = hiltViewModel(),
    modifier: Modifier,
    moveNextScreen: () -> Unit,
) {
    val listApi by viewModel.apiList.collectAsStateWithLifecycle()

    var tmpLatitude: String? = null
    var tmpLongitude: String? = null
    var tmpRange: String? = null

    if (CompanionObject.checkedBoolean) {
        tmpLatitude = CompanionObject.positionLatitude
        tmpLongitude = CompanionObject.positionLongitude
        tmpRange = CompanionObject.positionRange
    }

    viewModel.executeReposLoad(
        id = null,
        latitude = tmpLatitude,
        longitude = tmpLongitude,
        range = tmpRange
    )

    ListSet(
        list = listApi,
        moveScreen = moveNextScreen
    )
}

@Composable
fun ListSet(
    list: List<Shop>,
    moveScreen: () -> Unit
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
            var catchTextString: String = api.catchText ?: ""

            item {
                CardContent(
                    imageLink = api.logoImage!!,
                    storeName = api.name!!,
                    storePosition = api.smallArea!!.name!!,
                    storeCatch = catchTextString,
                    onClicked = {
                        moveScreen()
                        CompanionObject.shopID = api.id!!
                    }
                )
            }
        }
    }
}