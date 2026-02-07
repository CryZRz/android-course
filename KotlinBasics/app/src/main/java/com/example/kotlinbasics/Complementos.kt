package com.example.kotlinbasics

import java.sql.Time

const val fecha = "11-05-73"
lateinit var texto: String

//No tiene valor hasta ser utilizada !!!
val lazyTime: Long by lazy { System.currentTimeMillis() }

val global = 8
fun main() {
    println(global)
    println(local(1,2))

    //constantes
    //const val fecha = "adwadw" aqui no van
    println(Constantes.pi)
    println(Constantes.e)

    texto = readln()
    println(texto)
}

fun local(x: Int, y: Int): Int {
    println(global)
    return x+y
}