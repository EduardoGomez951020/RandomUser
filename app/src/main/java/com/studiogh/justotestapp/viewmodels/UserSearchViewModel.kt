package com.studiogh.justotestapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.studiogh.justotestapp.model.RandomUserResponse
import com.studiogh.justotestapp.repostories.UserRepository


class UserSearchViewModel(application: Application) :
    AndroidViewModel(application) {
    private var userRepository: UserRepository? = null
    var randomUserResponseLiveData: LiveData<RandomUserResponse?>? = null

    fun init() {
        userRepository = UserRepository()
        randomUserResponseLiveData = userRepository!!.getUserResponseLiveData()
    }

    fun searchUser() {
        userRepository!!.searchUserRequest()
    }
    fun getUserResponseLiveData(): LiveData<RandomUserResponse?>? {
        return randomUserResponseLiveData
    }
}