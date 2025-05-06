package com.example.testappdl.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testappdl.model.User.User
import com.example.testappdl.model.User.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val rep : UserRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val itemId: Int = savedStateHandle.get<Int>("itemId") ?: 0

    private val _selectedUser = MutableStateFlow<User>(rep.users.value.get(itemId))
    val selectedUser: StateFlow<User> = _selectedUser




}