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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testappdl.NavRoutes.ADD_TO_DATABASE_SCREEN
import com.example.testappdl.NavRoutes.DETAIL_SCREEN
import com.example.testappdl.component.UserItem
import com.example.testappdl.model.User.User
import com.example.testappdl.viewModel.HomeViewModel



@Composable
fun HomeScreen(
    navigate: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val users by viewModel.userData.collectAsState()



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

                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    itemsIndexed(items = users) { idx, item ->
                        UserItem(user = item, onClick = { navigate(DETAIL_SCREEN + "/${idx}") })

                    }
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