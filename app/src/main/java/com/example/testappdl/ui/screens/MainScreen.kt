package com.example.testappdl.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testappdl.NavRoutes.DETAIL_SCREEN
import com.example.testappdl.rep.User
import com.example.testappdl.ui.theme.TestAppDLTheme
import com.example.testappdl.ui.viewModel.MainViewModel
import kotlinx.coroutines.coroutineScope


@Composable
fun MainScreen(
    navigate: (String) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {

    var themeChange by rememberSaveable { mutableStateOf(true) }
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.statusBars.asPaddingValues())
    ) {
        Column {
            Row(modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = { themeChange = !themeChange },
                    modifier = Modifier.size(60.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Refresh,
                        contentDescription = "Change theme",
                        modifier = Modifier.size(48.dp)
                    )
                }
                Spacer(
                    modifier = Modifier.weight(1f)
                )
                Button(
                    onClick = { themeChange = !themeChange },
                    modifier = Modifier.size(60.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Change theme",
                        modifier = Modifier.size(48.dp)
                    )
                }

            }

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(items = viewModel.userData.value.toList()) { item ->
                    UserItem(user = item, onClick = { navigate(DETAIL_SCREEN) })

                }
            }


        }
    }
}

    @Composable
    fun UserItem(user: User, onClick: () -> Unit) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable { onClick.invoke() }
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Name:${user.name}")
                Text(text = "Surname:${user.surname}")
                Text(text = "Age ${user.age} years old")

            }
        }
    }

//@Composable
//@Preview(showBackground = true)
//fun PreviewMainScreen(){
//    TestAppDLTheme {
//        //MainScreen({})
//    }
//}