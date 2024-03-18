package com.example.restaurantfindkun.screen.top

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.restaurantfindkun.R
import dagger.Lazy

//
//メイン画面
//
@Composable
fun TopScreen(
) {
    val viewModel = viewModel<TopViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val listLazyListState = rememberLazyListState()

    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.statusBars)
                .padding(top = 80.dp)
        ) {
            SearchBarContent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, start = 16.dp, end = 16.dp),
                uiState = uiState,
                onEvent = viewModel::onEvent
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 80.dp),
                state = listLazyListState,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
//                val pokemonList = uiState.pokemonList
//
//                items(pokemonList) {
//                    PokemonListItem(
//                        pokemon = it,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                    )
//                }
            }
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
        onSearch = { active = false },
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
                    modifier = Modifier.padding(start = 16.dp),
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

    SearchBarContent(uiState, {})
}