package com.example.mobile_lab2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

// Адаптер для отображения списка персонажей в RecyclerView
class CharacterAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var characters = listOf<Character>()
    fun submitList (getMyCharacter: List<Character>){
        characters = getMyCharacter
        notifyDataSetChanged()
    }
    // Статические константы для типов отображения в RecyclerView
    companion object {
        const val TYPE_IMAGE = 1
        const val TYPE_NAME = 2
        const val TYPE_SPECIES = 3
    }

    // Создание ViewHolder в зависимости от типа отображения
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_IMAGE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
                ImageViewHolder(view)
            }
            TYPE_NAME -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_name, parent, false)
                NameViewHolder(view)
            }
            TYPE_SPECIES -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_species, parent, false)
                SpeciesViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    // Привязка данных к ViewHolder в зависимости от типа отображения
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val character = characters[position]

        when (holder.itemViewType) {
            TYPE_IMAGE -> (holder as ImageViewHolder).bind(character)
            TYPE_NAME -> (holder as NameViewHolder).bind(character)
            TYPE_SPECIES -> (holder as SpeciesViewHolder).bind(character)
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    // Получение типа отображения для позиции элемента
    override fun getItemViewType(position: Int): Int {
        return characters[position].getType()
    }

    // Получение общего количества элементов в списке
    override fun getItemCount(): Int = characters.size


    // ViewHolder для отображения изображения персонажа
    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)

        fun bind(character: Character) {
            Picasso.get().load(character.image).into(imageView)
        }
    }

    // ViewHolder для отображения имени персонажа
    inner class NameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)

        fun bind(character: Character) {
            nameTextView.text = character.name
        }
    }

    // ViewHolder для отображения вида персонажа
    inner class SpeciesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val speciesTextView: TextView = itemView.findViewById(R.id.speciesTextView)

        fun bind(character: Character) {
            speciesTextView.text = character.species
        }
    }
}
