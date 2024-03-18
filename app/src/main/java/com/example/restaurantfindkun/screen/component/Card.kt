package com.example.restaurantfindkun.screen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.restaurantfindkun.ui.theme.Black
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
    storeCatch: String
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
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
                        text = storeName,
                        style = StoreNameText,
                        color = Black
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


@Preview
@Composable
fun PreviewCardContent() {
    CardContent(
        "https://1.bp.blogspot.com/-ZOg0qAG4ewU/Xub_uw6q0DI/AAAAAAABZio/MshyuVBpHUgaOKJtL47LmVkCf5Vge6MQQCNcBGAsYHQ/s1600/pose_pien_uruuru_woman.png",
        "店舗名",
        "土地の名前",
        "テキストテキストテキストテキストテキストテキストテキストテキストテキストテキストテキストテキストテキストテキストテキストテキストテキストテキストテキストテキストテキスト"
    )
}