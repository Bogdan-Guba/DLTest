package com.example.testappdl.viewModel

import androidx.lifecycle.ViewModel
import com.example.testappdl.model.User.UserRepository
import com.example.testappdl.manager.themeManager.ThemeManager
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val rep : UserRepository
): ViewModel() {
    val userData = rep.users





}