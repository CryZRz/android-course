package com.example.jcintents

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.provider.AlarmClock
import android.provider.CalendarContract
import android.provider.ContactsContract
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.core.net.toUri

class LaunchIntent(private val context: Context) {
    fun shareText(){
        Log.i("Cryz", "shareText")
        val intent = Intent()
        intent.apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, "Cruso de android")
        }
        startIntent(intent)
    }

    private fun startIntent(intent: Intent){
        val chooser: Intent = Intent.createChooser(intent, context.getString(R.string.app_name))
        if (intent.resolveActivity(context.packageManager) != null){
            context.startActivity(chooser)
        }else{
            Toast.makeText(context, "No hay app compatibles", Toast.LENGTH_SHORT).show()
        }
    }

    fun setAlarm(){
        Log.i("Cryz", "setAlarm")
        val intent = Intent(AlarmClock.ACTION_SET_ALARM)
        intent.apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, "Chris")
            putExtra(AlarmClock.EXTRA_HOUR, 15)
            putExtra(AlarmClock.EXTRA_MINUTES, 15)
        }

        startIntent(intent)
    }
    fun setCalendar(){
        Log.i("Cryz", "setCalendar")
        val intent = Intent(Intent.ACTION_INSERT)
        intent.apply {
            data = CalendarContract.Events.CONTENT_URI
            putExtra(CalendarContract.Events.TITLE, "Hola mundo")
            putExtra(CalendarContract.Events.DESCRIPTION, "Je suis programeur")
            putExtra(CalendarContract.Events.EVENT_LOCATION, "México")
            putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, 1770175087000)
            putExtra(CalendarContract.EXTRA_EVENT_END_TIME, 1770239960000)
        }

        startIntent(intent)
    }
    fun openCamera(){
        Log.i("Cryz", "openCamera")
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startIntent(intent)

    }
    fun openFiles(){
        Log.i("Cryz", "openFiles")
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.apply {
            type = "image/*"
            addCategory(Intent.CATEGORY_OPENABLE)
            putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false)
        }
        (context as? MainActivity)?.galleryResul?.launch(intent)
    }
    fun openSettings(){
        Log.i("Cryz", "openSettings")
        val intent = Intent(Settings.ACTION_SETTINGS)
        startIntent(intent)
    }
    fun addContact(){
        Log.i("Cryz", "addContact")
        val intent = Intent(ContactsContract.Intents.Insert.ACTION)
        intent.apply {
            type = ContactsContract.RawContacts.CONTENT_TYPE
            putExtra(ContactsContract.Intents.Insert.NAME, "Chris")
            putExtra(ContactsContract.Intents.Insert.EMAIL, "Chris@mail.zoo")
            putExtra(ContactsContract.Intents.Insert.PHONE, "+546545646")
        }
        startIntent(intent)
    }
    fun dialPhone(){
        Log.i("Cryz", "dialPhone")
        val intent = Intent(Intent.ACTION_DIAL)
        val number = "546545646"
        intent.apply {
            data = "tel:$number".toUri()
        }
        startIntent(intent)
    }
    fun sendEmail(){
        Log.i("Cryz", "sendEmail")
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.apply {
            data = "mailto:".toUri()
            putExtra(Intent.EXTRA_EMAIL, arrayOf("Chris@mail"))
            putExtra(Intent.EXTRA_SUBJECT, "From android course")
            putExtra(Intent.EXTRA_TEXT, "Hola mundo")
        }

        startIntent(intent)
    }
    fun sendSms(){
        Log.i("Cryz", "sendSms")
        val smsUri = "smsto:65465464".toUri()
        val intent = Intent(Intent.ACTION_SENDTO, smsUri)

        intent.apply {
            putExtra("sms_body", "Je me appelle Chris")
        }

        startIntent(intent)
    }
    fun showMap(){
        Log.i("Cryz", "showMap")
        val lat = 45.72418
        val long = 4.857333
        val intent = Intent(Intent.ACTION_VIEW)
        intent.apply {
            data = "geo:0,0?q=$lat,$long(Hola mundo)".toUri()
            `package` = "com.google.android.apps.maps"
        }
        startIntent(intent)
    }
    fun searchMusic(){
        Log.i("Cryz", "searchMusic")
        val intent = Intent(MediaStore.INTENT_ACTION_MEDIA_SEARCH)
        intent.apply {
            putExtra(MediaStore.EXTRA_MEDIA_FOCUS, MediaStore.Audio.Artists.ENTRY_CONTENT_TYPE)
            putExtra(MediaStore.EXTRA_MEDIA_ARTIST, "video club")
            putExtra(SearchManager.QUERY, "video club")
        }

        startIntent(intent)
    }
    fun search(){
        Log.i("Cryz", "search")
        val intent = Intent(Intent.ACTION_WEB_SEARCH)
        intent.apply {
            putExtra(SearchManager.QUERY, "Terry davis")
        }

        startIntent(intent)
    }
    fun openWeb(){
        Log.i("Cryz", "openWeb")
        val web = "https://4chan.org/".toUri()
        val intent = Intent(Intent.ACTION_VIEW, web)
        startIntent(intent)
    }
}
