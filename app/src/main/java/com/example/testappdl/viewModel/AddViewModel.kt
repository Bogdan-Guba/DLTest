package com.example.testappdl.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testappdl.model.User.UserRepository
import com.example.testappdl.model.User.localrep.entity.UserRoom
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@HiltViewModel
class AddViewModel @Inject constructor(
    private val rep : UserRepository
): ViewModel() {

    fun addToDatabase(name: String, surname:String, age: Int){
        val userRoom = UserRoom(name = name, surname=surname, age=age)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                rep.userDao.insertUser(userRoom)
            }
        }

    }

}