package com.example.kotlinbasics

fun main() {
    //boleanos
    var varBol: Boolean = true

    println("Booleanos: ")
    println(varBol)

    //numeros
    println("Numeros enteros: ")
    val paises: Int = 120
    println("Max value ti int ${Int.MAX_VALUE}")
    println(paises)

    val milisegundos: Long = 21345010L
    println("Max value to long ${Long.MAX_VALUE}")
    println(milisegundos)

    //decimales
    println("Numeros decimales")
    val pesos: Float = 20.1f
    println("Max value in float ${Float.MAX_VALUE}")
    println(pesos)

    val diamtroLunar: Double = 2.102020101
    println("Max value in Double ${Double.MAX_VALUE}")
    println(diamtroLunar)

    //Caracteres
    println("Textos")
    val letra1: Char = 'J'
    val letra2: Char = 'A'
    val letra3: Char = 'Z'
    val letra4: Char = 'M'
    val letra5: Char = 'I'
    val letra6: Char = 'N'
    println("$letra1 $letra2 $letra3 $letra4 $letra5 $letra6")

    val nombre: String = "Jazmin"
    println(nombre)

    val espcialCases: String = "La ultima letra es la \"n\""
    println(espcialCases)

    //Concatenacion
    println("Concatenacion")
    val nombreDos = readln()
    println("Hola $nombreDos")

    //Tipo Nul
    println("Nulos")
    var sobrenombre: String? = null
    sobrenombre = "Chris"
    println("Longitud de sobrenombre ${sobrenombre!!.length}")
    sobrenombre = null
    println("Longitud de sobrenombre ${sobrenombre?.length}")

    println("Operador elvis")
    var version: Int? = null
    println("La version actual es ${version ?: 5}")
}