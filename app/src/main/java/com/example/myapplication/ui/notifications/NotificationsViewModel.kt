package com.example.myapplication.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NotificationsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text

    private val _number = MutableLiveData<Home>().apply {
        value = Home("hello", "whi whi whi")
    }
    val number: LiveData<Home> = _number


    fun changeText(){
        _text.value = "VARIABLES ARE INITIALIZE"
        val home = Home("hello", "How are you")
        _number.postValue(home)
    }

    fun changeTextByDialog(){
        _text.value = "POSITIVE DIALOG BUTTON"
    }
}