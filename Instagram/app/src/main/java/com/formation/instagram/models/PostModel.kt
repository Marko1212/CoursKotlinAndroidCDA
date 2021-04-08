package com.formation.instagram.models

import android.graphics.Bitmap
import android.os.Parcel
import android.os.Parcelable

data class PostModel(
        val id: Long,
        val user: UserModel,
        val image: Bitmap,
        // list de pseudo(s) pour savoir le nbr de likes total et connaître
        // si j'ai moi-même liké cette photo
        val users: List<String>,
        val description: String
): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readParcelable(UserModel::class.java.classLoader),
            parcel.readParcelable(Bitmap::class.java.classLoader),
            parcel.createStringArrayList(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeParcelable(user, flags)
        parcel.writeParcelable(image, flags)
        parcel.writeStringList(users)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PostModel> {
        override fun createFromParcel(parcel: Parcel): PostModel {
            return PostModel(parcel)
        }

        override fun newArray(size: Int): Array<PostModel?> {
            return arrayOfNulls(size)
        }
    }
}
