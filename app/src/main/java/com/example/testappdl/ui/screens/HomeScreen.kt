package com.example.testappdl.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testappdl.NavRoutes.ADD_TO_DATABASE_SCREEN
import com.example.testappdl.NavRoutes.DETAIL_SCREEN
import com.example.testappdl.component.UserItem
import com.example.testappdl.viewModel.HomeViewModel



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigate: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val users by viewModel.userData.collectAsState()
    val refreshState: PullToRefreshState= rememberPullToRefreshState()
    val isRefresh by viewModel.Refresh.collectAsState()


        Column {
            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(
                    modifier = Modifier.weight(1f)
                )
                Button(
                    onClick = { navigate(ADD_TO_DATABASE_SCREEN) },
                    modifier = Modifier.size(60.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Add user to DB",
                        modifier = Modifier.size(48.dp)
                    )
                }
            }
            PullToRefreshBox(
                modifier = Modifier.fillMaxSize(),
                onRefresh = { viewModel.updateUserData() },
                state = refreshState,
                isRefreshing = isRefresh

            ) {

                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                )
                {
                    itemsIndexed(
                        items = users,
                        key = { index, item -> item.id }
                    ) { idx, item ->
                        UserItem(
                            user = item,
                            onClick = { navigate(DETAIL_SCREEN + "/${idx}") },
                            onDelete = { user -> viewModel.deleteUser(user) }
                        )
                    }
                }
            }
        }

}