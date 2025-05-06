package com.example.testappdl.ui.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testappdl.rep.User
import com.example.testappdl.rep.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val rep : UserRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val itemId: Int = savedStateHandle.get<Int>("itemId") ?: 0

    private val _selectedUser = MutableStateFlow<User?>(null)
    val selectedUser: StateFlow<User?> = _selectedUser
    val colorScheme = rep.colorSheme

    init {
        viewModelScope.launch {
            rep.users.collect { userList ->
                _selectedUser.value = userList.getOrNull(itemId)
            }
        }
    }







}