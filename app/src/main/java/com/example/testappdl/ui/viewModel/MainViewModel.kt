package com.example.testappdl.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testappdl.rep.UserRepository
import com.example.testappdl.rep.User
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val rep : UserRepository
): ViewModel() {
    var userData = rep.users
    var colorScheme= rep.colorSheme

    fun changeTheme(){
        rep.changeTheme()
    }
}