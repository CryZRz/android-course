package com.example.xmlsqlite

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.xmlsqlite.databinding.ActivityAddBinding
import com.example.xmlsqlite.sqlite.Constants
import com.example.xmlsqlite.sqlite.DatabaseHelper
import com.google.android.material.snackbar.Snackbar

class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAddBinding.inflate(layoutInflater)

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupDatabase()
        setupButton()
    }

    private fun setupDatabase() {
        db = DatabaseHelper(this)
    }

    private fun setupButton() {
        binding.btnSave.setOnClickListener {
            val name = binding.tfName.editText?.text?.toString()?.trim() ?: ""
            if (name.isNotEmpty()){
                binding.tfName.error = null
                val park = Park(
                    name = name,
                    isFav = binding.cbFavorite.isChecked
                )
                savePark(park){ isSuccess ->
                    if (isSuccess){
                        finish()
                    }else{
                        Snackbar.make(binding.root, R.string.add_error_save, Snackbar.LENGTH_LONG).show()
                    }
                }
            }else{
                binding.tfName.error = getString(R.string.add_field_required)
            }
        }
    }

    private fun savePark(park: Park, onFinished: (Boolean) -> Unit){
        park.id = db.insertPark(park)

        onFinished(park.id != Constants.ERROR_ID)
        finish()
    }
}