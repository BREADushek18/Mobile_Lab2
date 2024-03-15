package com.example.mobile_lab2

// Класс Character представляет собой модель данных для персонажа
data class Character(
    val id: Int, val name: String, val species: String, val image: String
) {
    // Добавим пустой конструктор для Gson
    constructor() : this(0, "", "", "")

    // Метод getType() возвращает тип персонажа на основе вида
    fun getType(): Int {
        return when (species.toLowerCase()) {
            "human" -> CharacterAdapter.TYPE_IMAGE
            "alien" -> CharacterAdapter.TYPE_NAME
            else -> CharacterAdapter.TYPE_SPECIES
        }
    }
}
