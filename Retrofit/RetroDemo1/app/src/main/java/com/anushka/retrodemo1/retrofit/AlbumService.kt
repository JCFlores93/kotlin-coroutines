package com.anushka.retrodemo1.retrofit

import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumService {

  @GET("/albums/{id}")
  suspend fun getAlbum(@Path(value = "id")albumId:Int):Album

}