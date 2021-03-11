package com.basalam.androidtask.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.basalam.androidtask.model.MyPojo
import com.basalam.androidtask.network.Api
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class MyObjectViewModel : ViewModel() {

    private var listLiveData = MutableLiveData<List<MyPojo>>()
    private var errorLiveData = MutableLiveData<String>()

    private val errorScope = CoroutineExceptionHandler { coroutineContext, throwable ->
        errorLiveData.value = throwable.message
        Log.i("ERRORMSG", "ERRORMSG: " + throwable.message)
    }

     fun getAllObjects() : LiveData<List<MyPojo>> {

         try {
             viewModelScope.launch(Dispatchers.Main + errorScope) {
                 val list = Api.invoke().allObjects()
                 listLiveData.value = list
             }
         }catch (ex : IOException){
             ex.printStackTrace()
         }

         return listLiveData
    }


    interface OnError {
        fun onErrorEventInRequest(message : String)
    }

}