package com.studiogh.randomuser.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Name {
    @SerializedName("title")
    @Expose
    val title: String? = null

    @SerializedName("first")
    @Expose
    val first: String? = null

    @SerializedName("last")
    @Expose
    val last: String? = null
}