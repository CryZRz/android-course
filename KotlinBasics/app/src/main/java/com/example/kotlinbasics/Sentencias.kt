package com.example.kotlinbasics

fun main() {
    //Sentencias
    var x = readln().toInt()
    var y = readln().toInt()

    if (x == y){
        print("Son iguales")
    }
    else if (x > 3){
        print("Es mayor a 3")
    }else{
        println("Sentencia else")
    }

    //Sentencia when
    //La misma mierda que el switch
    println("Sentencia when")
    print("Insert your name ")
    val nombre = readln()
    when(nombre){
        "Christian" -> println("Christian")
        "Maria" -> println("Maria")
        "Jazmin" -> println("Jazmin")
        "Joy", "Guadalupe" -> println("Joy o guadalupe")
        "Alex" -> {
            println("Hola alex")
            println("Ke onda")
        }
        else -> println("Hola mundo")
    }
}