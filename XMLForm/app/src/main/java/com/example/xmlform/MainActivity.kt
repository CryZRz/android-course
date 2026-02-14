package com.example.xmlform

import android.graphics.Rect
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.widget.RadioButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.forEachIndexed
import androidx.lifecycle.lifecycleScope
import com.example.xmlform.databinding.ActivityMainBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var occupation: String = ""
    private var selectedDate: Long = 0L

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

        setupCheckBox()
        setupKeyboardListener()
        setupTextFields()
        setupDatePicker()
        setupRadioButton()
        setupButtons()
    }

    private fun setupButtons() {
        binding.btnTopSave.setOnClickListener { saveUser() }
        binding.btnBottomSave.setOnClickListener { saveUser() }
    }

    private fun saveUser(){
        with(binding){
            hideKeyboard(this@MainActivity)
            main.requestFocus()
            //showProgress
            progressContainer.visibility = View.VISIBLE


            lifecycleScope.launch {
                val nameValue = tfName.editText?.text.toString()
                val surnameValue = tfName.editText?.text.toString()
                val heightValue = tfName.editText?.text.toString()

                val errors = foundErrors(this@MainActivity, nameValue, surnameValue, heightValue)
                if (errors == null){
                    simulateDelayLong()
                    val user = User(
                        nameValue,
                        surnameValue,
                        heightValue.toInt(),
                        selectedDate,
                        occupation,
                        tfNotes.editText?.text.toString()
                    )
                    //dialog
                    openDialog(user)
                    Log.i("Chris", "onCreate: $user")
                }else{
                    Snackbar.make(root, errors, Snackbar.LENGTH_LONG).setAction(R.string.dialog_ok){}.show()
                }

                //hideProgress
                progressContainer.visibility = View.INVISIBLE
            }
        }
    }

    private fun openDialog(user: User){
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.dialog_title)
            .setMessage(userFormater(user))
            .setNegativeButton(R.string.dialog_cancel, null)
            .setPositiveButton(R.string.dialog_clean){ dialog, which ->
                cleanForm()
            }
                .show()
    }

    private fun setupRadioButton() {
        binding.rgProfile.setOnCheckedChangeListener { group, checkedId ->
            occupation = findViewById<RadioButton>(checkedId).text.toString()

            /*group.forEachIndexed { index, item ->
               if ((item as RadioButton).isChecked){
                   occupation = item.text.toString()
               }
           }*/

        }
    }

    private fun setupDatePicker() {
        binding.tfDate.editText?.apply {
            inputType = InputType.TYPE_NULL
            setOnClickListener { openDatePicker() }
        }
    }

    private fun openDatePicker() {
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()

        datePicker.addOnPositiveButtonClickListener {
            selectedDate = datePicker.selection ?: 0
            binding.tfDate.editText?.setText(convertMillisToDate(selectedDate))
        }

        datePicker.show(supportFragmentManager, "Chris")
    }

    private fun setupTextFields(){
        setupValidators(binding.tfName)
        setupValidators(binding.tfSurname)
        setupValidators(binding.tfHeight, resources.getInteger(R.integer.height_min_value))
    }

    private fun setupValidators(textField: TextInputLayout, minValue: Int = 0) {
        textField.editText?.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                val text = s ?: ""
                if (text.trim().isEmpty()){
                    textField.error = getString(R.string.supporting_required)
                }else {
                    if (minValue > 0 && (textField.editText?.toString()?.toIntOrNull() ?: 0) < minValue) {
                        textField.error = getString(R.string.error_min_height_valid)
                    } else {
                        textField.error = null

                    }
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun setupKeyboardListener() {
        binding.root.viewTreeObserver.addOnGlobalLayoutListener {
            val rect = Rect()
            binding.root.getWindowVisibleDisplayFrame(rect)
            if (binding.root.rootView.height - (rect.bottom - rect.top) > 500) {
                binding.btnTopSave.visibility = View.VISIBLE
                binding.btnBottomSave.visibility = View.INVISIBLE
            } else {
                binding.btnTopSave.visibility = View.INVISIBLE
                binding.btnBottomSave.visibility = View.VISIBLE
            }
        }
    }

    private fun setupCheckBox() {
        binding.cbAgree.setOnCheckedChangeListener { buttonView, isChecked ->
            binding.btnTopSave.isEnabled = isChecked
            binding.btnBottomSave.isEnabled = isChecked
        }
    }

    private fun cleanForm() {
        with(binding){
            tfName.editText?.setText("")
            tfSurname.editText?.setText("")
            tfNotes.editText?.setText("")

            tfDate.editText?.setText("")
            tfNotes.editText?.setText("")
            rbStudent.isChecked = true
            cbAgree.isChecked = false

            occupation = ""
            selectedDate = 0L

            tfName.error = null
            tfSurname.error = null
            tfHeight.error = null
        }
    }
}


