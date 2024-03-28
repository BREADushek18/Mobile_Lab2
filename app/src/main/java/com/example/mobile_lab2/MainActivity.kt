    package com.example.mobile_lab2

    import android.os.Bundle
    import androidx.activity.viewModels
    import androidx.appcompat.app.AppCompatActivity
    import androidx.recyclerview.widget.RecyclerView
    import androidx.recyclerview.widget.StaggeredGridLayoutManager
    import com.example.mobile_lab2.databinding.ActivityMainBinding
    class MainActivity : AppCompatActivity() {
        private lateinit var recyclerView: RecyclerView

        private lateinit var binding: ActivityMainBinding
        private val viewModel: ViewModelForCharacters by viewModels()
        private val characterAdapter = CharacterAdapter()

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
            setupRecyclerView()
            setupObservers()
            viewModel.fetchCharacters()

            // Находим RecyclerView в макете activity_main.xml
            recyclerView = findViewById(R.id.recyclerView)

        }
        private fun setupObservers() {
            // Настройка наблюдателя для списка персонажей в ViewModel
            viewModel.characters.observe(this) { characters ->
                // При получении нового списка персонажей обновляем данные в адаптере
                characterAdapter.submitList(characters)
            }
        }
        private fun setupRecyclerView() {
            binding.recyclerView.apply {
                // Устанавливаем менеджер компоновки для RecyclerView (в данном случае - StaggeredGridLayoutManager)
                layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                // Устанавливаем адаптер для RecyclerView
                adapter = characterAdapter
            }
        }
    }
