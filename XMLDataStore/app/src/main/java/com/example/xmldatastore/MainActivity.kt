package com.example.xmldatastore

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.xmldatastore.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val dataStoreSOurce: DataStoreSource by lazy { DataStoreSource(this) }
    private lateinit var adapter: CountryAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        setupRecyclerView()

        binding.btnAdd.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            lifecycleScope.launch {
                val country = binding.tfCountry.text.toString()
                addCountry(country)
                binding.tfCountry.setText("")
                binding.progressBar.visibility = View.INVISIBLE
            }
        }
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            val isFirstTime = dataStoreSOurce.getFirstTime()
            if (isFirstTime){
                setupDialog()
            }else{
                val username = dataStoreSOurce.getUsername()
                setupTitle(username)
                refreshAdapter()
            }
        }
    }

    private fun setupRecyclerView(){
        adapter = CountryAdapter()
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }

        adapter.submitList(listOf("Mexico", "España"))
    }

    private fun setupTitle(username: String) {
        binding.tvTitle.text = getString(R.string.main_title, username)
    }

    private fun setupDialog(){
        val dialogView = layoutInflater.inflate(R.layout.dialog_username, null)
        val dialog = MaterialAlertDialogBuilder(this)
            .setTitle(R.string.dialog_title)
            .setView(dialogView)
            .setCancelable(false)
            .setPositiveButton(R.string.dialog_confirm, null)
            .create()
        dialog.show()

        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
            val username = dialogView.findViewById<EditText>(R.id.tfUsername).text.toString()
            if (username.isBlank()){
                Toast.makeText(this, R.string.register_invalid, Toast.LENGTH_SHORT).show()
            }else{
                lifecycleScope.launch {
                    dataStoreSOurce.saveUsername(username)
                    Toast.makeText(this@MainActivity, R.string.register_success, Toast.LENGTH_SHORT).show()
                }
                setupTitle(username)
                dialog.dismiss()
            }
        }
    }

    private suspend fun addCountry(country: String) {
        try {
            dataStoreSOurce.addCountry(country)
            refreshAdapter()
            Log.i("Chris", "addCAountry $country")
        }catch (e: Exception){
            Log.i("Chris", "Fail $country")
        }
    }

    private suspend fun refreshAdapter(){
        try {
            binding.progressBar.visibility = View.VISIBLE
            adapter.submitList(dataStoreSOurce.getCountries())
        }finally {
            binding.progressBar.visibility = View.INVISIBLE
        }
    }
}
