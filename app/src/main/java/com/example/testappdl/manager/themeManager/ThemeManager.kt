package com.example.testappdl.manager.themeManager

import android.content.Context
import com.example.testappdl.manager.themeManager.ThemeManager.ThemeOption.Dark
import com.example.testappdl.manager.themeManager.ThemeManager.ThemeOption.Light
import com.example.testappdl.manager.themeManager.ThemeManager.ThemeOption.System
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
    private val _currentTheme = MutableStateFlow<ThemeOption>(System)
    val currentTheme=_currentTheme.asStateFlow()


    fun changeTheme(themeOption: ThemeOption) {
        when (themeOption) {
            Light -> {
                _colorScheme.value = false
                _currentTheme.value = Light
            }
            Dark -> {
                _colorScheme.value = true
                _currentTheme.value = Dark
            }
            System -> {
                _colorScheme.value = appContext.resources.configuration.isNightModeActive
                _currentTheme.value = System
            }
        }


    }

    sealed class ThemeOption(val label: String) {

        object System : ThemeOption("system")
        object Light : ThemeOption("light")
        object Dark : ThemeOption("dark")

    }
    companion object {

        val options = listOf(System, Light, Dark)
    }

}


