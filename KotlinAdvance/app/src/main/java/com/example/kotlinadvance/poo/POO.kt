package com.example.kotlinadvance.poo

fun main() {
    //Poo
    val casa = Building("Casa", "rio mayo", 477516231)
    val casa2 = Building("Casa", "centenario",)
    println(casa)
    println(casa2)

    //Constructor
    val edificio = Building("Jazmin", "Edificio", "10 de mayo")
    println(edificio)
    edificio.person = "Chris"
    println(edificio.person)
    println(edificio.getCamera("1234"))

    //Herencia
    val person2 = Person("Jazmin", "Barajas Solano")

    val anyValue: Any = 12
    //Forma insegura
    val anyToInt: Int = anyValue as Int

    if (anyValue is Int){
        println(anyValue+10)
    }

    val safe = anyValue as? Int
    if (safe != null){
        println(safe)
    }

    //Funciones de alcance

    //with
    with(casa){
        println(name)
    }

    //apply
    casa.apply {
        name = "Edificio"
    }

    //run
    casa.run {
        person = "Jhon, Marie"
        println(this)
        println(person)
    }

    //let
    val cinema = createCinema()
    cinema?.let { cm ->
        cm.person = "PePe LaLo"
        println(cm.person)
    }

    //also
    val market = Building("Plaza", "Centro").apply {
        number = 456.also {
            println("Hola mundo")
        }
    }
}


private fun createCinema(): Building?{
    return Building("Cine", "Principal")
}