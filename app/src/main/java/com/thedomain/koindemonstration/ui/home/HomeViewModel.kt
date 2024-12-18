package com.thedomain.koindemonstration.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Hello! Please provide a Username and your first name!"
    }
    val text: LiveData<String> = _text
}