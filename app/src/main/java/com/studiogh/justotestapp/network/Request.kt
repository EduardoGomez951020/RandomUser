package com.studiogh.justotestapp.network


import com.studiogh.justotestapp.model.RandomUserResponse
import retrofit2.Call
import retrofit2.http.GET


interface Request {
    @GET("https://randomuser.me/api/")
    fun searchUser(): Call<RandomUserResponse?>?
}