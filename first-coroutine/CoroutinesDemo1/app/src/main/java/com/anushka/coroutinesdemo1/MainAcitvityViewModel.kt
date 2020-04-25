package com.anushka.coroutinesdemo1

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.anushka.coroutinesdemo1.model.User
import com.anushka.coroutinesdemo1.model.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel:ViewModel() {
    private var userRepository = UserRepository()
    var users = liveData(Dispatchers.IO) {
        val result = userRepository.getUsers()
        emit(result)
    }
//    var users: MutableLiveData<List<User>> = MutableLiveData()
//
//    fun getUserData() {
//        viewModelScope.launch {
//            //write some code
//            var result: List<User>? = null
//            withContext(Dispatchers.IO){
//                result = userRepository.getUsers()
//            }
//            users.value = result
//        }
//    }
}