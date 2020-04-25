package com.anushka.retrodemo1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData

class MainActivityViewModel(private val eventManager: UIEventManager) : ViewModel() {
    val albumsRepository = AlbumsRepository()
    val album = liveData {
        val receivedAlbum = albumsRepository.getAlbum(5)
        emit(receivedAlbum)
    }

    fun getSingleAlbum(id: Int) = liveData {
        val receivedAlbum = albumsRepository.getAlbum(id)
        emit(receivedAlbum)
    }

    fun getSingleAlbumResponse(id: Int) = liveData {

            eventManager.viewProgressBar()
            val receivedAlbumResponse = albumsRepository.getAlbumResponse(id)
            eventManager.stopProgressBar()
            eventManager.viewToast("Successfully Done")
            emit(receivedAlbumResponse)
    
    }


}