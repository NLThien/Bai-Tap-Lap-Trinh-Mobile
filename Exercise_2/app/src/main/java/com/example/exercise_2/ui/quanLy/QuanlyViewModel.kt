package com.example.exercise_2.ui.quanLy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuanlyViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Hệ Thống"
        value = "Quản Lý Thư Viện"
    }
    val text: LiveData<String> = _text
}