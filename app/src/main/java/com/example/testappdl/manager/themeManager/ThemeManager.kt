package com.example.testappdl.manager.themeManager

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ThemeManager @Inject constructor(
    @ApplicationContext private val appContext: Context

) {
    private val _colorScheme =
        MutableStateFlow<Boolean>(appContext.resources.configuration.isNightModeActive)
    val colorScheme: StateFlow<Boolean>
        get() = _colorScheme.asStateFlow()

    fun changeTheme() {
        _colorScheme.value= !_colorScheme.value
    }
}