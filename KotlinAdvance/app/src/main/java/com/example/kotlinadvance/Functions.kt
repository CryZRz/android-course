package com.example.kotlinadvance

import java.util.Scanner

fun main() {
    sayHello(age = 20)

    //lamda
    val lambda = {x:Int -> x*x}
    println(lambda(5))
    showFoods("ATUN", "HUEVO", "SALCHICHA", "LEMONY")

    //funcionaes de orden superior
    println(eval(1,2, {z -> println(z) }))
    println(eval(1,2) { z -> println(z) })
}

fun eval(x: Int, y: Int, onResult: (z: Int) -> Unit){
    var z = 0
    Thread{
        Thread.sleep(2_000)
        val multli = 2
        z = (x+y)*multli
        onResult(z)
    }.start()
}
fun showFoods(vararg foods: String){
    for (food in foods){
        println(food)
    }
}
fun sayHello(name: String = "Jazmin", age: Int){
    println("Your name is $name and your age is $age")
}
fun sayHello(name: String){
    println("Hola $name")
}

fun pi(): Double {
   return Math.PI
}