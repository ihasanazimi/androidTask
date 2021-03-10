package com.basalam.androidtask.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.basalam.androidtask.model.MyPojo
import com.basalam.androidtask.network.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyObjectViewModel : ViewModel() {

    var myLiveData = MutableLiveData<List<MyPojo>>()

    fun getAllObjects(errorListening: OnError): LiveData<List<MyPojo>> {
        Api.invoke().allObjects().enqueue(object : Callback<List<MyPojo>> {
            override fun onResponse(call: Call<List<MyPojo>>, response: Response<List<MyPojo>>) {
                myLiveData.value = response.body()
            }


            override fun onFailure(call: Call<List<MyPojo>>, t: Throwable) {
                errorListening.onErrorEvent()
            }
        })
        return myLiveData
    }


    interface OnError {
        fun onErrorEvent()
    }

}