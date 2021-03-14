package com.basalam.androidtask.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.basalam.androidtask.model.MyPojo
import java.io.IOException

class Rep(var context: Context) {

    // instance of Room dataBase
    private val db = RoomDB.getDataBase(context.applicationContext)
    private val pojoLiveData = MutableLiveData<List<MyPojo>>()

    fun getAllItems(): LiveData<List<MyPojo>> {
        val v = db.dao().getAllObj()
        pojoLiveData.value = v
        return pojoLiveData
    }

    fun getItemById(uid: Long): LiveData<List<MyPojo>> {
        val v = db.dao().searchObjById(uid)
        pojoLiveData.value = v
        return pojoLiveData
    }

    fun deleteItem(deleteItemModel: MyPojo) {
        return db.dao().deleteObject(deleteItemModel)
    }

    fun deleteAllItems() {
        db.dao().deleteAll()
    }

    fun updateItem(updateItemModel: MyPojo) {
        db.dao().update(updateItemModel)
    }


    fun insertAll(list: List<MyPojo>) {
        try {
            for (i in list) {
                val uid = db.dao().insertObj(i)
                i.uid = uid
            }
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
    }

    /** if return a number > 0 : means != -1 -> success
     * else not success!
     */
    fun insertItemAndReturnUid(newItem: MyPojo): Long {
        val uid = db.dao().insertObj(newItem)
        newItem.uid = uid
        return uid
    }
}