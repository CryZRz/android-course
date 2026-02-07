package com.example.xmlcomponents

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.URLUtil
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.OptIn
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.xmlcomponents.databinding.ActivityMainBinding
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import com.google.android.material.badge.ExperimentalBadgeUtils
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import java.net.URL

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @OptIn(ExperimentalBadgeUtils::class)
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

        setSupportActionBar(binding.bottomAppBar)

        binding.fab.setOnClickListener {
            if (binding.bottomAppBar.fabAlignmentMode == BottomAppBar.FAB_ALIGNMENT_MODE_CENTER){
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
            }else{
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
            }
        }

        binding.bottomAppBar.setNavigationOnClickListener {
            Log.i("criz", "Hola mundo")
            Snackbar.make(binding.root, R.string.message_succes_event, Snackbar.LENGTH_SHORT)
                .setAnchorView(binding.fab)
                .show()
        }

        binding.content.btnBuy.setOnClickListener {
            Snackbar.make(binding.root, R.string.message_buy, Snackbar.LENGTH_LONG)
                .setAction(R.string.action_go){
                    Toast.makeText(this,R.string.message_record, Toast.LENGTH_SHORT).show()
                }
                .setAnchorView(binding.fab)
                .show()
        }

        loadImage("https://goo.gl/gEgYUd")

        /*
        findViewById<MaterialButton>(R.id.btnBuy).setOnClickListener {
            Toast.makeText(this, "Historial", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.btnSkip).setOnClickListener {
            findViewById<MaterialCardView>(R.id.cvAd).visibility = View.GONE
        }
        */

        /*
        binding.content.btnBuy.setOnClickListener {
            Toast.makeText(this, "Historial", Toast.LENGTH_SHORT).show()
        }*/

        binding.content.btnSkip.setOnClickListener {
            findViewById<MaterialCardView>(R.id.cvAd).visibility = View.GONE
        }

        binding.content.tfEmail.onFocusChangeListener = View.OnFocusChangeListener{ view, focused -> {
                if (focused){
                    binding.content.tfEmail.setBackgroundColor(Color.CYAN)
                }else{
                    binding.content.tfEmail.setBackgroundColor(Color.GRAY)
                }
            }
        }


        binding.content.btnTemp.setOnClickListener {
            loadImage(binding.content.tfUrl.text.toString())
        }

        binding.content.tfUrl.addTextChangedListener {
            var errorStr: String? = null
            val url = binding.content.tfUrl.text.toString()

            when{
                url.isEmpty() -> {
                    errorStr = getString(R.string.helper_text_required)
                }

                URLUtil.isValidUrl(url) -> {
                    loadImage(url)
                }
                else -> {
                    errorStr = getString(R.string.error_invalid_url)
                }
            }

            loadImage(url)

            binding.content.tilUrl.error = errorStr
        }

        binding.content.cbEnablePassword.setOnClickListener {
            binding.content.tilPassword.isEnabled = !binding.content.tilPassword.isEnabled
        }

        binding.content.swFab.setOnCheckedChangeListener { buttonView, isChecked ->   {
            if (isChecked){
                buttonView.text = getString(R.string.sw_hide_fab)
                binding.fab.show()
            }else{
                buttonView.text = getString(R.string.sw_show_fab)
                binding.fab.hide()
            }
        } }

        binding.content.cpEmail.setOnCheckedChangeListener { chip, isChecked -> {
            Toast.makeText(this, chip.text.toString(), Toast.LENGTH_SHORT).show()
        } }

        binding.content.cpEmail.setOnCloseIconClickListener {
            binding.content.cpEmail.visibility = View.GONE
        }

        binding.content.sldVolumen.addOnChangeListener { slider, value, fromUser -> {
            binding.content.cpEmail.text = getString(R.string.sld_value, value)
        } }

        binding.content.btgColors.addOnButtonCheckedListener { group,  checkedId, isChecked -> {
            binding.root.setBackgroundColor(
                when(checkedId){
                    R.id.btnRed -> Color.RED
                    R.id.btnBlue -> Color.BLUE
                    R.id.btnGreen -> Color.GREEN
                    else -> Color.MAGENTA
                }
            )
        } }

        val badge = BadgeDrawable.create(this)
        badge.number = 21
        binding.content.containerNotifications.foreground = badge
        binding.content.containerNotifications.addOnLayoutChangeListener { view, left,top, right, bottom, oldLeft, oldTop, oldRight, oldBottom -> {
            BadgeUtils.attachBadgeDrawable(badge, binding.content.ibNotifications, binding.content.containerNotifications)
        } }

        binding.content.okBtn.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle(R.string.dialog_title)
                .setMessage(R.string.dialog_message)
                .setNeutralButton(R.string.btn_skip){ dialog,which -> {
                    Log.i("Cryz", "Saltar")
                } }
                .setNeutralButton(R.string.btn_cancel){ dialog, which -> {
                    Log.i("Cryz", "Cancelar")
                } }
                .setPositiveButton(R.string.common_ok){ dialog,which -> {
                    Log.i("Cryz", "Aceptar")
                } }
        }
    }

    private fun loadImage(url: String){
        Glide.with(this)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .placeholder(R.drawable.ic_timmer)
            .error(R.drawable.ic_broken_image)
            .centerCrop()
            .into(binding.content.imgUrl)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_exit -> {
                Toast.makeText(this, R.string.message_bay, Toast.LENGTH_SHORT).show()
                super.finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}