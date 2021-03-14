package com.basalam.androidtask.network

import com.basalam.androidtask.model.MyPojo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface Api {

    //    https://jsonplaceholder.typicode.com/photos?albumid=1

    @GET("photos")
    suspend fun allObjects(@Query("albumid") albumId : Int) : List<MyPojo>


    companion object {
        private const val baserUrl = "https://jsonplaceholder.typicode.com/"
        operator fun invoke(): Api {
            return Retrofit.Builder()
                .baseUrl(baserUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Api::class.java)
        }
    }
}