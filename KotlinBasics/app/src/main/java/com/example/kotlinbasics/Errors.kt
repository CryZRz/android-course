package com.example.kotlinbasics

fun main() {
    try {
        //Throw
        val ex = "Lucy"
        val nombre = readln()

        if (nombre == ex){
            throw Exception("K onda gente")
        }else{
            println("K onda gente")
        }
    }catch (e: Exception){
        println(e.toString())
    }finally {
        println("Siempre se ejecuta")
    }
}