package com.example.kotlinbasics

fun main() {
    print("Operadores")
    var x = readln()
    var y = readln()
    println("${x.toInt() + y.toInt()}")
    println("${x.toInt() - y.toInt()}")
    println("${x.toInt() * y.toInt()}")
    println("${x.toInt() / y.toInt()}")
    println("${x.toInt() % y.toInt()}")

    var numx = x.toInt()
    var numy = x.toInt()
    numx++
    numy--
    numx+=11
    numy-=12
    println(numx)
    println(numy)

    val esIgual = numx == numy
    val esDiferente = numx != numy
    println(esIgual)
    println(esDiferente)

    //Operadors
    val or = numx == 3 || numy == 1
    val and = numx == 3 && numy == 1

    //Comparacion
    val mayorQue = numx > 10
    val menorQue = numx < 10
    val MoreOrEquals = numx >= 10
    val lessOrEquals = numx <= 10
    println(mayorQue)
    println(menorQue)
}