package com.example.testappdl.viewModel

import androidx.lifecycle.ViewModel
import com.example.testappdl.model.User.UserRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject


    @HiltViewModel
    class LoginScreenViewModel @Inject constructor(
        private val rep : UserRepository
    ): ViewModel() {




    }
