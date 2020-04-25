package com.anushka.retrodemo1

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.anushka.retrodemo1.retrofit.Album

class MainActivityViewModel: ViewModel() {
    val albumRepository = AlbumRepository()
    val album = liveData {
        val receivedAlbum = albumRepository.getAlbum(5)
        emit(receivedAlbum)
    }
}