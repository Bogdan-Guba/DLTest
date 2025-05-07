package com.example.testappdl.ui.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testappdl.R
import com.example.testappdl.manager.themeManager.ThemeManager.ThemeOption
import com.example.testappdl.viewModel.SettingViewModel

@Composable
fun SettingScreen(
    viewModel: SettingViewModel = hiltViewModel(),
) {

    val selectedOption = viewModel.selectedTheme.collectAsState().value


    val appVersion = stringResource(id=R.string.app_version)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text("Выберите тему:", style = MaterialTheme.typography.titleMedium)

        Column {
            viewModel.listOfThemes.forEach { option ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (option == selectedOption),
                            onClick = { viewModel.changeTheme(option) },
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (option == selectedOption),
                        onClick = null
                    )
                    Text(
                        text = option.label,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }


    Spacer(
        modifier = Modifier.weight(1f)
    )
        Button(
            onClick = {

                viewModel.deleteAllUserData()
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Удалить все пользовательские данные")
        }

        // --- Версия приложения ---
        Text(text = "Версия приложения: $appVersion", style = MaterialTheme.typography.bodySmall)
    }
}