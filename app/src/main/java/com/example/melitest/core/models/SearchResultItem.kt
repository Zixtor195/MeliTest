package com.example.melitest.core.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class SearchResultItem(
    @SerializedName("id") val id: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("price") val price: Double,
    @SerializedName("thumbnail") val thumbnail: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString()
    )


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeDouble(price)
        parcel.writeString(thumbnail)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SearchResultItem> {
        override fun createFromParcel(parcel: Parcel): SearchResultItem {
            return SearchResultItem(parcel)
        }

        override fun newArray(size: Int): Array<SearchResultItem?> {
            return arrayOfNulls(size)
        }
    }
}