package com.basalam.androidtask.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MyFactory(app : App) : ViewModelProvider.AndroidViewModelFactory(app) {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return super.create(modelClass)
    }

}