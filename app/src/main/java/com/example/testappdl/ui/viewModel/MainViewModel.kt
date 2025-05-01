package com.example.testappdl.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testappdl.rep.User
import dagger.hilt.android.lifecycle.HiltViewModel


class MainViewModel:ViewModel() {
    private var _user = MutableLiveData<User>()

    private var _users = MutableLiveData<List<User>>()



    fun setUsers(users:List<User>){
        _users.value=users
    }

    fun setUser(user:User){
        _user.value=user
    }




}