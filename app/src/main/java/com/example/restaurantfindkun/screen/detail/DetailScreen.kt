package com.example.restaurantfindkun.screen.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CarCrash
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.CreditCardOff
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.NoCrash
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.restaurantfindkun.screen.component.CardDetails
import com.example.restaurantfindkun.screen.component.CompanionObject
import com.example.restaurantfindkun.screen.result.ResultViewModel
import com.example.restaurantfindkun.ui.theme.Black
import com.example.restaurantfindkun.ui.theme.DetailStoreNameText
import com.example.restaurantfindkun.ui.theme.StoreCatchText

@Composable
fun DetailScreen(
    resultViewModel: ResultViewModel = hiltViewModel(),
    modifier: Modifier,
    moveNextScreen: () -> Unit
) {
    val listApi by resultViewModel.apiList.collectAsStateWithLifecycle()

    resultViewModel.executeReposLoad(
        id = CompanionObject.shopID,
        latitude = null,
        longitude = null,
        range = null,
    )

    var imageLinkString = ""
    var storeNameString = ""
    var openTimeString = ""
    var closeTimeString = ""
    var accessString = ""
    var cardBoolean = false
    var wifiBoolean = false
    var parkingBoolean = false

    listApi.forEach { api ->
        imageLinkString = api.photo?.mobile?.l ?: ""
        storeNameString = api.name ?: ""
        openTimeString = api.open ?: ""
        closeTimeString = api.close ?: ""
        accessString = api.mobileAccess ?: ""
        cardBoolean = api.card == "利用可"
        wifiBoolean = api.wifi == "あり"
        parkingBoolean = api.parking!!.startsWith("あり")
    }

    DetailContent(
        imageLink = imageLinkString,
        storeName = storeNameString,
        openTime = openTimeString,
        closeTime = closeTimeString,
        access = accessString,
        card = cardBoolean,
        wifi = wifiBoolean,
        parking = parkingBoolean
    )
}

@Composable
fun DetailContent(
    imageLink: String,
    storeName: String,
    openTime: String,
    closeTime: String,
    access: String,
    card: Boolean,
    wifi: Boolean,
    parking: Boolean
) {
    Column(
        modifier = Modifier
            .padding(top = 65.dp)
            .windowInsetsPadding(WindowInsets.statusBars)
            .fillMaxSize()
    ) {
        AsyncImage(
            model = imageLink,
            contentDescription = "店舗ロゴ画像",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .aspectRatio(1f / 1f)
        )
        Column(
            modifier = Modifier
                .padding(top = 5.dp, start = 12.dp, end = 12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.width(320.dp),
                    text = storeName,
                    style = DetailStoreNameText,
                    color = Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Icon(
                    modifier = Modifier
                        .size(40.dp)
                        .fillMaxSize(),
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "お気に入りボタン（機能なし）",
                    tint = Black
                )
            }
            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = "開店：$openTime",
                style = StoreCatchText,
                color = Black
            )
            Text(
                modifier = Modifier.padding(top = 5.dp),
                text = "閉店：$closeTime",
                style = StoreCatchText,
                color = Black
            )
            Text(
                modifier = Modifier.padding(top = 5.dp),
                text = "アクセス：$access",
                style = StoreCatchText,
                color = Black
            )
            Row(
                modifier = Modifier
                    .padding(top = 5.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CardDetails(
                    okBoolean = card,
                    detailIcon = Icons.Default.CreditCard,
                    detailNGIcon = Icons.Default.CreditCardOff,
                    detailText = "カード"
                )
                CardDetails(
                    okBoolean = wifi,
                    detailIcon = Icons.Default.Wifi,
                    detailNGIcon = Icons.Default.WifiOff,
                    detailText = "Wifi"
                )
                CardDetails(
                    okBoolean = parking,
                    detailIcon = Icons.Default.NoCrash,
                    detailNGIcon = Icons.Default.CarCrash,
                    detailText = "駐車場"
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewDetailContent() {
    DetailContent(
        imageLink = "https://imgfp.hotp.jp/IMGH/01/36/P036040136/P036040136_69.jpg",
        storeName = "舗店名舗店名舗店名舗店名舗店名舗店名舗店名舗店名舗店名舗店名",
        openTime = "月～木、土、日、祝日: 16:00～23:00 （料理L.O. 22:30 ドリンクL.O. 22:45）金、祝前日: 16:00～翌5:00 （料理L.O. 翌4:30 ドリンクL.O. 翌4:45）",
        closeTime = "【年中無休で営業中】団体様用個室やフロア貸切のご予約受付中！",
        access = "JR五反田駅東口 徒歩1分/宴会向けｺｰｽ3500円～",
        card = true,
        wifi = false,
        parking = true
    )
}