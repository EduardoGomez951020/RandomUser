package com.studiogh.justotestapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Results {
    @SerializedName("name")
    @Expose
    var name: Name? = null

    @SerializedName("email")
    @Expose
    val email: String? = null

    @SerializedName("phone")
    @Expose
    val phone: String? = null

    @SerializedName("cell")
    @Expose
    val cell: String? = null

    @SerializedName("picture")
    @Expose
    var picture: Picture? = null
}