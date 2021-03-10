package com.basalam.androidtask.network

import com.basalam.androidtask.model.MyPojo
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface Api {

    @Headers("")
    @GET("")
    fun allObjects() : Call<List<MyPojo>>


    companion object {
        private const val baserUrl = " ...... "
        operator fun invoke(): Api {
            return Retrofit.Builder()
                .baseUrl(baserUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Api::class.java)
        }
    }
}