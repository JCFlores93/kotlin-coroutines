package com.anushka.coroutinesdemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class MainActivity : AppCompatActivity() {
    private var count = 0
    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
//        mainActivityViewModel.getUserData()
        mainActivityViewModel.users.observe(this, Observer { myUsers ->
            myUsers.forEach{
                Log.i("MyTag5", "name is ${it.name}")
            }
        })
//        CoroutineScope(Dispatchers.IO).launch {
//            Log.i("My tag", "Hello form ${Thread.currentThread().name}")
//        }
//
//        CoroutineScope(Dispatchers.Main).launch {
//            Log.i("My tag", "Hello form ${Thread.currentThread().name}")
//        }
        btnCount.setOnClickListener {
            tvCount.text = count++.toString()
        }

//        btnDownloadUserData.setOnClickListener {
//            CoroutineScope(IO).launch {
//                downloadUserData()
//            }
//        }
        btnDownloadUserData.setOnClickListener {
            CoroutineScope(Main).launch {
//                tvUserMessage.text = UserDataManager().getTotalUserCount().toString()
                tvUserMessage.text = UserDataManager2().getTotalUserCount().toString()
            }
        }

        btnDownloadLocationData.setOnClickListener{
            CoroutineScope(IO).launch {
                downloadLocalData()
            }
        }

//        CoroutineScope(IO).launch {
        CoroutineScope(Main).launch {
            Log.i("MyTag", "Calculation started")
//            val stock1 = async(IO){ getStock1()}
//            val stock2 = async(IO){ getStock2() }
            // Inician cuanto se llama .await()
            val stock1 = async(start = CoroutineStart.LAZY){ getStock1()}
            val stock2 = async(start = CoroutineStart.LAZY){ getStock2() }
            // to demostrate another task within the coroutine
            delay(15 * 1000)
            Log.i("MyTag", "************ Just before await **************")
            val total = stock1.await() + stock2.await()
            Log.i("MyTag", "Total is $total")
            Toast.makeText(applicationContext, "Total is $total", Toast.LENGTH_LONG).show()
        }

        CoroutineScope(Main).launch {
           withTimeoutOrNull(300) {
//               withTimeout(3 * 1000) {
                   withContext(IO) {
                       for (i in 1..10) {
                           delay(1 * 100)
                           Log.i("MyTag", "round $i")
                       }
                   }
               }
//           }
        }
        Log.i("MyTag2", "This is the start of the onCreate")
        runBlocking(IO) {
            for(i in 1..10) {
                delay(100)
                Log.i("MyTag2", "round $i in ${Thread.currentThread().name}")
            }
        }
        Log.i("MyTag2", "This is the end 1 of the onCreate")

        Log.i("MyTag2", "This is the end 2 of the onCreate")


        // Join Function
        CoroutineScope(Main).launch {
            Log.i("MyTag4", "Coroutines launched from $${Thread.currentThread().name}")
            DemoClass().test1()
            Log.i("MyTag4", "Came back to main activity $${Thread.currentThread().name}")
        }
    }

    private suspend fun downloadUserData() {
        for (i in 1..200000) {
            withContext(Main) {
                Log.i("MyTag", "Downloading user $i in ${Thread.currentThread().name}")
                tvUserMessage.text = "Downloading user $i in ${Thread.currentThread().name}"
            }
//            delay(3000)
        }
    }

    private suspend fun downloadLocalData() {
        for (i in 1..200000) {
            withContext(Main) {
                Log.i("MyTag", "Downloading local $i in ${Thread.currentThread().name}")
                tvLocationMessage.text = "Downloading location $i in ${Thread.currentThread().name}"
            }
//            delay(3000)
        }
    }


}

private suspend fun getStock1(): Int {
    Log.i("My Tag", "counting stock 1 started")
    delay(10 * 1000)
    Log.i("My Tag", "stock 1 returned")
    return 55000
}

private suspend fun getStock2(): Int {
    Log.i("My Tag", "counting stock 2 started")
    delay(8 * 1000)
    Log.i("My Tag", "stock 2 returned")
    return 35000
}