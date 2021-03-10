package com.basalam.androidtask.network

import com.basalam.androidtask.model.MyPojo
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface Api {

    @GET("")
    fun allObjects() : Call<List<MyPojo>>






    companion object {
        private const val baserUrl = " ...... "
        operator fun invoke(): Api {
            return Retrofit.Builder()
                .baseUrl(baserUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Api::class.java)
        }
    }
}