package com.basalam.androidtask.repository

import androidx.room.*
import com.basalam.androidtask.model.MyPojo

@Dao
interface MyDao {

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    fun insertObj(yourObject : MyPojo) : Long

    @Delete
    fun deleteObject(yourObject : MyPojo)

    @Query("SELECT * FROM MyPojo;")
    fun getAllObj() : List<MyPojo>

    @Query("SELECT * FROM MyPojo WHERE uid =:uid;")
    fun searchObjById(uid : Long) :  List<MyPojo>

    @Query("Delete From MyPojo")
    fun deleteAll()

    @Update
    fun update(yourObject: MyPojo)

}