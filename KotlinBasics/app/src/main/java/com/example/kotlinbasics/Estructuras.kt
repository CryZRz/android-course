package com.example.kotlinbasics

fun main() {
    //Lista inmutable
    var dias: List<String>
    dias = listOf("Lunes", "Martes", "Miercoles", "Jueves", "Viernes")
    for (day in dias){
        println(day)
    }

    var amigos: MutableList<String>
    amigos = mutableListOf("Blinzzia", "Victor", "Juanpa", "Alex")
    amigos.add("Alan")
    amigos.remove("Blinzzia")
    amigos[3] = "Alejandro"

    for (amigo in amigos){
        println(amigo)
    }

    //Diccionarios Maps
    val map = mutableMapOf<String, Int>()
    map["TP"] = 2
    map["DOOM"] = 10
    map["Pacas"] = 12
    map["TP1"] = 13

    map.remove("TP")
    map["DOOM"] = 21

    for((key, value) in map){

    }

    //Transformaciones
    var precios = listOf<Int>(10,20,5)
    println(precios.map { precio -> precio * 0.16 })

    val bebidas = listOf<String>("Soda", "Cerveza", "Agua")
    println(bebidas.zip(precios))

    val numeros = listOf(listOf(1,2,3), listOf(4,5,6))
    println(numeros.flatten())

    val herrameintas = listOf("Martillo", "Tablas", "Tuercas")
    herrameintas.filter { herrameinta -> herrameinta.length > 10}

    println(herrameintas.sorted())
    println(herrameintas.reversed())

}