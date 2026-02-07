package com.example.jcform

import android.content.Context
import java.text.Format
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

fun convertMillisToDate(millis: Long): String{
    val dateFormater = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
        .withLocale(Locale.getDefault())

    return LocalDateTime.ofInstant(
        Instant.ofEpochMilli(millis),
        ZoneId.of("UTC")
    ).format(dateFormater)
}

fun foundErrors(context: Context, name:String, surname: String, height: String): String?{
    return if (name.isBlank() || surname.isBlank() ||
            (height.toIntOrNull() ?: 0) < context.resources.getInteger(R.integer.height_max_value)
        ){
        context.getString(R.string.form_title)
    }else{
        null
    }
}

fun userFormater(user: User): String{
    val result = StringBuilder()
    result.append("Nombre: ${user.name} ${user.surname}\n")
    result.append("Estatura: ${user.height}\n")
    result.append("Fecha de nacimiento: ${convertMillisToDate(user.birdDate)}\n")
    result.append("Ocupacion: ${user.occupation}\n")
    result.append("Notas: ${user.notes}\n")

    return result.toString()
}