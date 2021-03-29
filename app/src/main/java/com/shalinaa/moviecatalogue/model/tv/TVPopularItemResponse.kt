package com.shalinaa.moviecatalogue.model.tv

import com.google.gson.annotations.SerializedName

data class TVPopularItemResponse(
    @SerializedName("poster_path")
    val poster_path:String,

    @SerializedName("title", alternate = ["name"])
    val title:String,

    @SerializedName("popularity")
    val popularity:Double,

    @SerializedName("original_language")
    val original_language:String
)
