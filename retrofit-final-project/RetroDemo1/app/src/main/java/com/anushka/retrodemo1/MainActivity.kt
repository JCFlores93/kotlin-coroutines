package com.anushka.retrodemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.anushka.retrodemo1.retrofit.Album
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), UIEventManager {
    override fun viewProgressBar() {
        progressBar.visibility=View.VISIBLE
    }

    override fun stopProgressBar() {
        progressBar.visibility=View.GONE
    }

    override fun viewToast(message : String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        val viewModelFactory = MainActivityViewModelFactory(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainActivityViewModel::class.java)

        submit_button.setOnClickListener {
            getAlbumResponse(input_edit_text.text.toString().toInt())
        }
    }

    private fun getAlbum(id: Int) {
//        viewModel.album.observe(this, Observer {
//               textView.text = it.title
//        })

        viewModel.getSingleAlbum(id).observe(this, Observer {
            textView.text = it.title
        })
    }

    private fun getAlbumResponse(id: Int) {
        viewModel.getSingleAlbumResponse(id).observe(this, Observer {
            //get album instance and other information here
            val album: Album? = it.body()
            textView.text = album?.title
            val isSuccessful: Boolean = it.isSuccessful
            Log.i("MyTAG", " is successful? : $isSuccessful")
            val responseCode: Int = it.code()
            Log.i("MyTAG", " response code : $responseCode")
            textView.text = album?.title
        })
    }

}
