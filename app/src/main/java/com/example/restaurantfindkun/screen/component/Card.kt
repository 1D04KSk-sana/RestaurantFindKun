package com.example.restaurantfindkun.screen.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.CreditCardOff
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.restaurantfindkun.ui.theme.Black
import com.example.restaurantfindkun.ui.theme.Gray
import com.example.restaurantfindkun.ui.theme.KariColor
import com.example.restaurantfindkun.ui.theme.Pink
import com.example.restaurantfindkun.ui.theme.StoreCatchText
import com.example.restaurantfindkun.ui.theme.StoreNameText
import com.example.restaurantfindkun.ui.theme.StorePositionText

//
//共通：カード
//

//コンテンツ一覧表示用
@Composable
fun CardContent(
    imageLink: String,
    storeName: String,
    storePosition: String,
    storeCatch: String,
    onClicked: () -> Unit
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .clickable(onClick = onClicked)
            .defaultMinSize(minHeight = 100.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                model = imageLink,
                contentDescription = "店舗ロゴ画像",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(5.dp)
                    .weight(1f)
                    .aspectRatio(1f / 1f)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .weight(3f)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.width(250.dp),
                        text = storeName,
                        style = StoreNameText,
                        color = Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Icon(
                        modifier = Modifier
                            .size(25.dp)
                            .fillMaxSize(),
                        imageVector =Icons.Default.FavoriteBorder,
                        contentDescription = "お気に入りボタン（機能なし）",
                        tint = Black
                    )
                }
                Text(
                    text = storePosition,
                    style = StorePositionText,
                    color = Black
                )
                Text(
                    text = storeCatch,
                    style = StoreCatchText,
                    color = Black,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun CardDetails(
    okBoolean: Boolean,
    detailIcon: ImageVector,
    detailNGIcon: ImageVector,
    detailText: String,
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .size(height = 100.dp, width = 120.dp)
            .fillMaxSize()
            .padding(5.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = detailText,
                style = StoreNameText,
                color = if (okBoolean) Pink else Gray
            )
            Icon(
                modifier = Modifier
                    .size(65.dp)
                    .fillMaxSize(),
                imageVector = if (okBoolean) detailIcon else detailNGIcon,
                contentDescription = "各カードの詳細をあらわすアイコン",
                tint = if (okBoolean) Pink else Gray
            )
        }
    }
}

@Preview
@Composable
fun PreviewCardContent() {
//    CardContent(
//        "https://imgfp.hotp.jp/IMGH/01/36/P036040136/P036040136_69.jpg",
//        "店舗名舗店名舗店名舗店名舗店名舗店名",
//        "土地の名前",
//        "テキストテキストテキストテキストテキストテキストテキストテキストテキストテキストテキストテキストテキストテキストテキストテキストテキストテキストテキストテキストテキスト",
//        {}
//    )
    CardDetails(
        okBoolean = true,
        detailIcon = Icons.Default.CreditCard,
        detailNGIcon = Icons.Default.CreditCardOff,
        detailText = "カード"
    )
}