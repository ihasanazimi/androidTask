package com.basalam.androidtask.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class MyPojo (

    @ColumnInfo(name = "uid")
    @PrimaryKey
    @SerializedName("id")
    var id: Long = 0,

    @ColumnInfo(name = "Title")
    @SerializedName("Title")
    var title: String,

    @ColumnInfo(name = "Description")
    @SerializedName("Description")
    var description: String,

    @ColumnInfo(name = "ImageUrl")
    @SerializedName("ImageUrl")
    var imageUrl: String



) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(imageUrl)
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
