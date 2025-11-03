package com.example.nebeng.feature_a_history_order.presentation.history_order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HistoryOrderViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is history order Fragment"
    }
    val text: LiveData<String> = _text
}