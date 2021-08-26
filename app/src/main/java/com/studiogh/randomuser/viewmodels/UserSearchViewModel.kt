package com.studiogh.randomuser.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.studiogh.randomuser.model.RandomUserResponse
import com.studiogh.randomuser.repostories.UserRepository


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