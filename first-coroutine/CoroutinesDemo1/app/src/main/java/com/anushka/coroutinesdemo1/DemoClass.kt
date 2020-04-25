package com.anushka.coroutinesdemo1

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DemoClass {
    suspend fun test1() {
        coroutineScope {
            Log.i("MyTag4", "Top $${Thread.currentThread().name}")
            val job = launch(Dispatchers.IO) {
                Log.i("MyTag4", "Before Delay $${Thread.currentThread().name}")
                delay(1000)
                Log.i("MyTag4", "After Delay $${Thread.currentThread().name}")
            }
            job.join()
            Log.i("MyTag4", "Bottom $${Thread.currentThread().name}")
        }
    }
}