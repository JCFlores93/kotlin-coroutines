package com.anushka.retrodemo1

import com.anushka.retrodemo1.retrofit.Album
import com.anushka.retrodemo1.retrofit.AlbumService
import com.anushka.retrodemo1.retrofit.RetrofitInstance
import retrofit2.Response

class AlbumsRepository {

    var albumService: AlbumService = RetrofitInstance.albumService

    suspend fun getAlbum(albumId: Int): Album = albumService.getAlbum(albumId)

    suspend fun getAlbumResponse(albumId: Int): Response<Album> =
        albumService.getAlbumResponse(albumId)

}
