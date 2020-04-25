package com.anushka.coroutinesdemo1

import kotlinx.coroutines.*

class UserDataManager2 {
    var count = 0
    lateinit var deferred:Deferred<Int>

    suspend fun getTotalUserCount(): Int {
        // This coroutineScope guarantees to complete all the works started by coroutines
        // within the child scopr before the return of the suspending function.
        coroutineScope {
            launch(Dispatchers.IO) {
                delay(1000)
                count = 50
            }

            deferred = async(Dispatchers.IO) {
                delay(3 * 1000)
                return@async 70
            }
        }
        return count + deferred.await()
    }
}