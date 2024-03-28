package com.example.mobile_lab2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelForCharacters(application: Application) : AndroidViewModel(application) {
    private val _characters = MutableLiveData<List<Character>>()
    val characters: LiveData<List<Character>> =_characters

    private val _isError = MutableLiveData<Boolean>(false)
    private val _errorMessage = MutableLiveData<String>()


    // асинхронный запрос
    fun fetchCharacters() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitClient.createApiServer().getCharacters()
                if (response.isSuccessful) {
// ответ успешно получен
                    _characters.postValue(response.body()?.results)
                    _isError.postValue(false)
                } else {
// API-ошибка
                    _isError.postValue(true)
                    _errorMessage.postValue("Error ${response.code()}: ${response.message()}")
                }
            } catch (e: Exception) {
// ошибки-исключения
                _isError.postValue(true)
                _errorMessage.postValue(e.localizedMessage ?: "Unknown error occurred")
            }
        }
    }
}