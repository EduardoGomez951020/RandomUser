package com.studiogh.justotestapp.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class RandomUserResponse {
    @SerializedName("results")
    @Expose
    var items: List<Results>? = null
}