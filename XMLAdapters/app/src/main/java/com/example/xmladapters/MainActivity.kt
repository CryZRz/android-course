package com.example.xmladapters

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.xmladapters.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), onClickListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setUpSpinner()
        setupRecyclerViewFriends()
        //setupRecycleViewFoods()
        setupToggleButton()
    }

    private fun setupToggleButton() {
        binding.toggleButton.addOnButtonCheckedListener { group, checkId, isChecked ->
            when(checkId){
                R.id.btnFriends -> if (isChecked) setupRecyclerViewFriends()
                R.id.btnFoods -> if (isChecked) setupRecycleViewFoods()
            }
        }
    }

    private fun setupRecyclerViewFriends() {
        val firends = getAllFriends()
        val friendsAdapter = TextAdapter(firends)

        binding.recycleView.apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = friendsAdapter
        }
    }

    private fun setUpSpinner(){
        val countries = getAllCountries()
        val countriesAdapter = ArrayAdapter(this, android.R.layout.simple_selectable_list_item, countries)
        binding.acCountries.setAdapter(countriesAdapter)
    }

    private fun setupRecycleViewFoods(){
        val foods = getAllFoods()
        //val foodsAdapter = TextAdapter(foods.map { it.name })
        val foodsAdapter = FoodsAdapter(foods, this)

        binding.recycleView.apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = foodsAdapter
        }
    }

    override fun onCLick(food: Food) {
        Toast.makeText(this, food.name, Toast.LENGTH_SHORT).show()
    }
}