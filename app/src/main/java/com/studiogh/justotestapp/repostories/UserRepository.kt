package com.studiogh.justotestapp.repostories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.studiogh.justotestapp.model.RandomUserResponse
import com.studiogh.justotestapp.network.Request
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class UserRepository {
    private val searchUserURL = "https://randomuser.me/api//"
    private val userSearchService: Request
    private val randomUserResponseLiveData: MutableLiveData<RandomUserResponse?> = MutableLiveData()
    fun searchUserRequest() {
        userSearchService.searchUser()
            ?.enqueue(object : Callback<RandomUserResponse?> {
                override fun onResponse(
                    call: Call<RandomUserResponse?>?,
                    response: Response<RandomUserResponse?>
                ) {
                    if (response.body() != null)
                        randomUserResponseLiveData.postValue(response.body())
                }

                override fun onFailure(call: Call<RandomUserResponse?>?, t: Throwable?) {
                    randomUserResponseLiveData.postValue(null)
                }
            })
    }

    fun getUserResponseLiveData(): LiveData<RandomUserResponse?> {
        return randomUserResponseLiveData
    }

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        userSearchService = Retrofit.Builder()
            .baseUrl(searchUserURL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Request::class.java)
    }

}