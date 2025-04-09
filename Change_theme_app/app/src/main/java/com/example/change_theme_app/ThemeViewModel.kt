package com.example.change_theme_app

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

class ThemeViewModel : ViewModel() {
    var backgroundColor by mutableStateOf(Color.White)
        private set

    fun changeBackgroundColor(color: Color) {
        backgroundColor = color
    }
}