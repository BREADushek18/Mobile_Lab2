package com.example.mobile_lab2

import retrofit2.Call
import retrofit2.http.GET

interface RickAndMortyApiService {
    @GET("character")
    fun getCharacters(): Call<Results>
}
