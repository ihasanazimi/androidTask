package com.basalam.androidtask.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.basalam.androidtask.model.MyPojo

@Database(entities = [MyPojo::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class RoomDB : RoomDatabase() {

    // Dao`s
    abstract fun dao(): MyDao

    companion object {

        @Volatile
        var database: RoomDB? = null

        // singleTon design pattern

        fun getDataBase(context: Context): RoomDB {
            val tempInstance = database
            if (database != null) {
                return tempInstance as RoomDB
            }

            //synchronized  --- means -->  access just one there on main thread!

            synchronized(this) {

                val instance =
                    Room.databaseBuilder(context.applicationContext, RoomDB::class.java, "database")
                        .fallbackToDestructiveMigration().allowMainThreadQueries().build()

                database = instance
                return instance
            }
        }
    }
}