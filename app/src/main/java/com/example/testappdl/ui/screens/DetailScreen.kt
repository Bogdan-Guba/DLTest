package com.example.testappdl.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testappdl.NavRoutes.HOME_SCREEN
import com.example.testappdl.model.User.User
import com.example.testappdl.viewModel.DetailViewModel



@Composable
fun DetailScreen(
    navigate: (String) -> Unit,
    itemId : Int,
    viewModel: DetailViewModel = hiltViewModel()
) {



        val user: User by viewModel.selectedUser.collectAsState()

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.statusBars.asPaddingValues())
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "Name : ${user.name}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "Surname : ${user.surname}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "Age : ${user.age}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Data destination : ${user.dataDestination.mark}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center
                )

                Button(
                    onClick = { navigate(HOME_SCREEN) },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Close",
                        modifier = Modifier.size(48.dp)
                    )
                }
            }
        }

}


//@Preview(showBackground = true)
//@Composable
//fun PreviewDetailScreen() {
//    TestAppDLTheme {
//
//        DetailScreen({})
//    }
//}