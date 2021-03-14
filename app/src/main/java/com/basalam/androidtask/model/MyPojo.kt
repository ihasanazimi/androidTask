package com.basalam.androidtask.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MyPojo(

    @ColumnInfo(name = "uid")
    @PrimaryKey
    var uid: Long,
    @ColumnInfo(name = "albumId")
    var albumId: Int,
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "url")
    var url: String,
    @ColumnInfo(name = "thumbnailUrl")
    var thumbnailUrl: String,

    ) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(uid)
        parcel.writeInt(albumId)
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(url)
        parcel.writeString(thumbnailUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MyPojo> {
        override fun createFromParcel(parcel: Parcel): MyPojo {
            return MyPojo(parcel)
        }

        override fun newArray(size: Int): Array<MyPojo?> {
            return arrayOfNulls(size)
        }
    }
}
