package com.example.mobile_lab2

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface RickAndMortyApiService {
    @GET(RickAndMortyApp.ENDPOINT_CHARACTERS)
    suspend fun getCharacters(): Response<Results>
}

object RetrofitClient {
    fun createApiServer(): RickAndMortyApiService{
        return Retrofit.Builder()
                .baseUrl(RickAndMortyApp.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RickAndMortyApiService::class.java)
    }
}