package com.stslex.meal.data.entity

import com.google.gson.annotations.SerializedName

data class ImageEntity(
    @SerializedName("id") val id: String,
    @SerializedName("urls") val urls: RemoteUrlsModel
) {

    data class RemoteUrlsModel(
        @SerializedName("raw") val raw: String,
        @SerializedName("full") val full: String,
        @SerializedName("regular") val regular: String,
        @SerializedName("small") val small: String,
        @SerializedName("thumb") val thumb: String
    )
}
