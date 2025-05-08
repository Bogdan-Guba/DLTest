package com.example.testappdl.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testappdl.model.User.UserRepository
import com.example.testappdl.manager.themeManager.ThemeManager
import com.example.testappdl.model.User.User
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val rep : UserRepository
): ViewModel() {
    val userData = rep.users

    fun deleteUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            rep.deleteUser(user)
        }

    }





}