package com.magang.kelilingapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


data class Wisata (
    @SerializedName("data") val data: List<Datum>
)

@Parcelize
data class Datum (
    @SerializedName("caption") val caption: String,
    @SerializedName("description") val description: String,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("address") val address: String,
    @SerializedName("longitude")val longitude: Double,
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("like") val like: String,
    @SerializedName("rating") val rating: Double,
    @SerializedName("image1") val image1: String,
    @SerializedName("image2") val image2: String,
    @SerializedName("image3") val image3: String,
    @SerializedName("image4") val image4: String,
    @SerializedName("image5") val image5: String,
    @SerializedName("video") val video: String,
): Parcelable
