package com.example.testappdl.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testappdl.rep.UserRepository
import com.example.testappdl.rep.localrep.entity.UserRoom
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@HiltViewModel
class AddViewModel @Inject constructor(
    private val rep : UserRepository
): ViewModel() {
    var colorScheme= rep.colorSheme

    fun addToDatabase(userRoom: UserRoom){

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                rep.userDao.insertUser(userRoom)
            }
        }

    }

}