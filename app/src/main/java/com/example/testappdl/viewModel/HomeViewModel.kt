package com.example.testappdl.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testappdl.model.User.UserRepository
import com.example.testappdl.manager.themeManager.ThemeManager
import com.example.testappdl.model.User.User
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val rep : UserRepository
): ViewModel() {
    val userData = rep.users
    private val _isRefresh = MutableStateFlow(false)
    val Refresh= _isRefresh.asStateFlow()


    fun deleteUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            rep.deleteUser(user)
        }

    }

    fun updateUserData(){
        viewModelScope.launch(Dispatchers.IO) {
            _isRefresh.value =true
            rep.updateUserRepository()
            _isRefresh.value =false
        }
    }





}