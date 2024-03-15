package com.example.mobile_lab2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Находим RecyclerView в макете activity_main.xml
        recyclerView = findViewById(R.id.recyclerView)
        // Устанавливаем менеджер компоновки для RecyclerView (StaggeredGridLayoutManager)
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        // Создаем экземпляр Retrofit для работы с сетью
        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Создаем экземпляр сервиса, используя интерфейс RickAndMortyApiService
        val apiService = retrofit.create(RickAndMortyApiService::class.java)

        // Вызываем метод getCharacters() на apiService и обрабатываем ответ
        apiService.getCharacters().enqueue(object : Callback<Results> {
            override fun onResponse(call: Call<Results>, response: Response<Results>) {
                if (response.isSuccessful) {
                    // Получаем данные персонажей из ответа
                    val charactersData = response.body()
                    charactersData?.let {
                        // Создаем адаптер и передаем контекст MainActivity и данные персонажей
                        adapter = CharacterAdapter(this@MainActivity, charactersData)
                        // Устанавливаем адаптер для RecyclerView
                        recyclerView.adapter = adapter
                    }
                } else {
                    // Логируем ошибку, если запрос не успешен
                    Log.d("MainActivity", "Failed to load characters")
                }
            }

            override fun onFailure(call: Call<Results>, t: Throwable) {
                // Логируем ошибку при неудачном выполнении запроса
                Log.e("MainActivity", "Error loading characters: ${t.message}")
            }
        })
    }
}
