package com.example.testappdl.viewModel

import androidx.lifecycle.ViewModel
import com.example.testappdl.model.User.UserRepository
import com.example.testappdl.model.themeManager.ThemeManager
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
     themeManager: ThemeManager
): ViewModel() {
    val isDarkTheme = themeManager.colorSheme


}