package com.example.testappdl.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.testappdl.manager.themeManager.ThemeManager
import com.example.testappdl.model.User.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class SettingViewModel @Inject constructor(
    val themeManager: ThemeManager,
    private val rep : UserRepository
):ViewModel() {
   val listOfThemes = ThemeManager.options
   val selectedTheme = themeManager.currentTheme

    fun deleteAllUserData(){
        viewModelScope.launch(Dispatchers.IO){

            rep.deleteAllUserData()
        }

    }
    fun changeTheme(nameOptions: ThemeManager.ThemeOption){
        themeManager.changeTheme(nameOptions)

    }





}