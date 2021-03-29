package com.shalinaa.moviecatalogue.model.tv

import com.google.gson.annotations.SerializedName
import com.shalinaa.moviecatalogue.model.movie.MovieItemResponse

data class PopularResponse(
    @SerializedName("page")
    val page:Int,
    @SerializedName("results")
    val result: ArrayList<TVPopularItemResponse>
)
