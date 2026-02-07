package com.example.kotlinbasics

fun main() {
    //Bucles o ciclos
    for (i in 1..10){
        println(i)
    }

    val nombre = "Jazmin"
    for (letter in nombre){
        print(letter)
    }
    println()
    nombre.map { c -> print(c) }
    println()
    nombre.forEach { print(it) }
    println()

    var indice = 0
    while (indice < nombre.length){
        println("Hola")
        indice++
    }

    do {
        println("K onda")
        indice--
    }while (indice > 0)
}