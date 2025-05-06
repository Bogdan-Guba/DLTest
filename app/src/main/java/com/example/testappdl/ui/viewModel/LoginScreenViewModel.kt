package com.example.testappdl.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.testappdl.rep.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject


    @HiltViewModel
    class LoginScreenViewModel @Inject constructor(
        private val rep : UserRepository
    ): ViewModel() {

        var colorScheme= rep.colorSheme


    }
