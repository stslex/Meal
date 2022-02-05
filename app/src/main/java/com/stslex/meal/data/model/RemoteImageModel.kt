package com.stslex.meal.data.model

import com.google.gson.annotations.SerializedName

data class RemoteImageModel(
    @SerializedName("id") val id: String,
    @SerializedName("urls") val urls: RemoteUrlsModel
)
