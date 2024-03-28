package com.example.mobile_lab2

import android.app.Application

class RickAndMortyApp : Application() {
    companion object {
        const val BASE_URL = "https://rickandmortyapi.com/api/"
        const val ENDPOINT_CHARACTERS = "character"
    }

}