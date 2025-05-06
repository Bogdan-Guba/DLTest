package com.example.testappdl.model.themeManager

import android.content.Context
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
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
    private val _colorSheme =
        MutableStateFlow<Boolean>(appContext.resources.configuration.isNightModeActive)
    val colorSheme: StateFlow<Boolean>
        get() = _colorSheme.asStateFlow()

    fun changeTheme() {
        _colorSheme.value= !_colorSheme.value
    }
}