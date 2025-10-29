package com.example.nebeng.feature_homepage.presentation.homepage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomepageViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is homepage Fragment"
    }
    val text: LiveData<String> = _text
}