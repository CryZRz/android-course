package com.example.xmlsqlite

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.xmlsqlite.databinding.ActivityMainBinding
import com.example.xmlsqlite.sqlite.DatabaseHelper
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ParkAdapter
    private lateinit var db: DatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupDatabase()
        setupRecyclerView()
        setupEfab()
    }

    private fun setupDatabase() {
        db = DatabaseHelper(this)
    }

    private fun setupEfab() {
        binding.efab.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView() {
        adapter = ParkAdapter(mutableListOf(), this)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    override fun onResume() {
        super.onResume()
        refreshData()
    }

    private fun refreshData(){
        val parks = db.getAllParks()
        parks.forEach { park ->
            adapter.add(park)
        }
    }

    override fun onClick(park: Park) {
        if (!db.updatePark(park)){
            Snackbar.make(binding.root, R.string.main_error_uopdate, Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onLongClick(park: Park) {
        MaterialAlertDialogBuilder(this)
            .setTitle(getString(R.string.dialog_title))
            .setMessage(getString(R.string.dialog_message_warning))
            .setNegativeButton(getString(R.string.dialog_cancel),null)
            .setPositiveButton(getString(R.string.dialog_delete)){ dialog, which ->
                deletePark(park)
            }.show()
    }

    private fun deletePark(park: Park){
        if (db.deletePark(park)){
            adapter.remove(park)
        }else{
            Snackbar.make(binding.root, R.string.main_error_remove, Snackbar.LENGTH_LONG).show()
        }
    }
}