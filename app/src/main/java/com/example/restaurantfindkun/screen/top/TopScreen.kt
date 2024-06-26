package com.example.restaurantfindkun.screen.top

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.restaurantfindkun.R
import com.example.restaurantfindkun.screen.component.CheckBoxContent
import com.example.restaurantfindkun.screen.component.CompanionObject
import com.example.restaurantfindkun.screen.component.DropDownMenuContent
import com.example.restaurantfindkun.ui.theme.Black
import com.example.restaurantfindkun.ui.theme.SearchText

//
//メイン画面
//
@Composable
fun TopScreen(
    viewModel: TopViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    moveNextScreen: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    //ComboBox（DropDownMenu）用の現在位置からの検索範囲データ
    val testItems = listOf("1", "2", "3", "4", "5")
    var selectedItem by remember { mutableStateOf(testItems.first()) }

    viewModel.getLocation(LocalContext.current)

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.statusBars)
                .padding(top = 70.dp)
        ) {
            SearchBarContent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, start = 16.dp, end = 16.dp),
                uiState = uiState,
                onEvent = viewModel::onEvent,
                onSearchEvent = {
                    if (!CompanionObject.checkedBoolean) {
                        //
                        //エラー：MessageダイアログをAlertDialogを用いて表示しようと頑張ったけど
                        // @Composable invocations can only happen from the context of a @Composable function
                        //のエラーが解決できず何もしない状態にしている
                        //
                    } else {
                        moveNextScreen();
                    }
                }
            )
            CheckBoxContent(titleName = "現在位置からの範囲で検索する") { isChecked ->
                CompanionObject.checkedBoolean = !CompanionObject.checkedBoolean
            }
            Text(
                text = "・現在位置からの検索範囲",
                color = Black,
                style = SearchText
            )
            DropDownMenuContent(
                modifier = Modifier,
                items = testItems,
                selectedItem = selectedItem,
                onItemSelected = {
                    selectedItem = it
                    CompanionObject.positionRange = selectedItem
                }
            )
        }
    }
}

//
//検索バー
//
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarContent(
    uiState: UiState,
    onEvent: (SearchBarEvent) -> Unit,
    onSearchEvent: () -> Unit,
    modifier: Modifier = Modifier
) {

    var active by rememberSaveable { mutableStateOf(false) }

    val query = uiState.query
    val isQuerying = uiState.isQuerying
    val result: List<String> = uiState.historyTestList

    DockedSearchBar(
        modifier = modifier,
        query = query,
        onQueryChange = {
            onEvent(SearchBarEvent.QueryChange(it))
        },
        onSearch = {
            active = false
            onSearchEvent()
        },
        active = active,
        onActiveChange = {
            active = it
        },
        leadingIcon = {
            if (active) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "戻るボタン",
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .clickable {
                            onEvent(SearchBarEvent.Back)
                            active = false
                        },
                )
            } else {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "検索ボタン",
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .clickable { },
                )
            }
        },
        trailingIcon = {
            if (isQuerying) {
                Icon(
                    imageVector = Icons.Default.Cancel,
                    contentDescription = "キャンセルボタン",
                    modifier = Modifier
                        .padding(12.dp)
                        .size(32.dp)
                        .clickable {
                            onEvent(SearchBarEvent.Cancel)
                            active = false
                        },
                )
            }
        },
        placeholder = {
            Text(
                text = stringResource(id = R.string.searchBarText)
            )
        },
    ) {
        // Search result shown when active
        if (result.isNotEmpty()) {
            //検索結果があるときの行動
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                //検索結果のリスト
                items(items = result) { history ->
                    ListItem(
                        headlineContent = { Text(text = history) },
                        leadingContent = {
                            Icon(
                                imageVector = Icons.Default.History,
                                contentDescription = "履歴アイコン"
                            )
                        },
                    )
                }
            }
        } else {
            //検索結果がないときの行動
            Text(
                text = stringResource(id = R.string.searchBarNone)
            )
        }
    }
}

//
//プレビュー：メイン画面
//
@Preview
@Composable
fun PreviewMainScreen() {
    val viewModel = viewModel<TopViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SearchBarContent(uiState, {}, {})
}