package com.anushka.coroutinesdemo1.model

import kotlinx.coroutines.delay

class UserRepository {
    suspend fun getUsers(): List<User> {
        delay(8 * 1000)
        val users: List<User> = listOf(
            User(1,"Jean"),
            User(2,"Tony"),
            User(3,"Nalia"),
            User(4,"Romel"),
            User(5,"Carlos")
        )

        return users
    }
}