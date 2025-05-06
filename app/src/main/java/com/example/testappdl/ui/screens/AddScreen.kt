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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testappdl.NavRoutes.MAIN_SCREEN
import com.example.testappdl.viewModel.AddViewModel

@Composable
fun AddScreen(
    navigate: (String) -> Unit,
    viewModel: AddViewModel =hiltViewModel()
) {
    var name by rememberSaveable { mutableStateOf("") }
    var surname by rememberSaveable { mutableStateOf("") }
    var age by rememberSaveable { mutableStateOf("") }
    var err by rememberSaveable { mutableStateOf("TEXT ERRPRORS") }



        Surface(
            modifier = Modifier
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
                    text = "Add user to local database",
                    textAlign = TextAlign.Center
                )
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Name") },
                    onValueChange = { name = it },
                    value = name
                )
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Surname") },
                    onValueChange = { surname = it },
                    value = surname
                )
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Age") },
                    onValueChange = { age = it },
                    value = age
                )

                Text(
                    modifier = Modifier.fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    text = err,
                    color = Color.Red,
                    textAlign = TextAlign.Center
                )

                Button(
                    modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally),
                    onClick = {
                        viewModel.addToDatabase(name, surname, age.toInt())
                        navigate(MAIN_SCREEN)
                    }) {
                    Text("Add to database")
                }


            }
        }

}


