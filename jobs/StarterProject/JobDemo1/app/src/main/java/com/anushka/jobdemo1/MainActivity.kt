package com.anushka.jobdemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class MainActivity : AppCompatActivity() {
    lateinit var job1: Job
    lateinit var deferred1: Deferred<Int>
    var returnedValue:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        job1 =  CoroutineScope(Main).launch {
//            downloadData()
            deferred1 = async { downloadData2() }
            returnedValue = deferred1.await()
            Log.i("Mytag", "**** $returnedValue ****")
        }

        cancelButton.setOnClickListener{
//            job1.cancel()
            deferred1.cancel()
        }

        statusButton.setOnClickListener{
//            if(job1.isActive) {
            if(deferred1.isActive) {
                textView.text = "Active"
//            } else if(job1.isCancelled) {
            } else if(deferred1.isCancelled) {
                textView.text = "Cancelled"
//            } else if(job1.isCompleted) {
            } else if(deferred1.isCompleted) {
                textView.text = "Completed"
            }
        }
    }

    private suspend fun downloadData() {
        withContext(IO){
            repeat(30) {
                delay(1000)
                Log.i("MyTag3", "repeating $it")
            }
        }
    }

    private suspend fun downloadData2(): Int {
        var i = 0
        withContext(IO){
            repeat(30) {
                delay(1000)
                Log.i("MyTag3", "repeating $it")
                i++
            }
        }
        return i
    }

}
