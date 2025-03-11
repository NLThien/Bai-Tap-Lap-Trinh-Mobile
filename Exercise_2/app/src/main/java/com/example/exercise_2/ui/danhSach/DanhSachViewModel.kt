package com.example.exercise_2.ui.danhSach

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DanhSachViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Chưa có gì đâu dừng soi"
    }
    val text: LiveData<String> = _text
}