package com.example.testappdl.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.testappdl.NavRoutes.MAIN_SCREEN
import com.example.testappdl.ui.theme.TestAppDLTheme
import com.example.testappdl.ui.viewModel.DetailViewModel
import com.example.testappdl.ui.viewModel.LoginScreenViewModel

@Composable
fun LoginScreen(
    navigate: (String) -> Unit,
    //viewModel: LoginScreenViewModel = hiltViewModel()
){
    var login by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var error by rememberSaveable { mutableStateOf("TEXT ERRPRORS") }
    Surface(modifier = Modifier
        .fillMaxSize()
        .padding(WindowInsets.statusBars.asPaddingValues())
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                text = "Welcome to my app",
                textAlign = TextAlign.Center
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Login") },
                onValueChange = { login = it },
                value = login
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Password") },
                onValueChange = { password = it },
                value = password
            )
            Text(
                modifier = Modifier.fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                text = error,
                color = Color.Red,
                textAlign = TextAlign.Center
            )

            Button(
                modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally),
                onClick = { navigate(MAIN_SCREEN) }) {
                Text("To the next Screen")
            }


        }
    }
}




@Composable
@Preview(showBackground = true)
fun PreviewLoginScreen(){
    TestAppDLTheme {
        LoginScreen({})

    }
}