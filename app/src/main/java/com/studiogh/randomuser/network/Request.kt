package com.studiogh.randomuser.network


import com.studiogh.randomuser.model.RandomUserResponse
import retrofit2.Call
import retrofit2.http.GET


interface Request {
    @GET("https://randomuser.me/api/")
    fun searchUser(): Call<RandomUserResponse?>?
}