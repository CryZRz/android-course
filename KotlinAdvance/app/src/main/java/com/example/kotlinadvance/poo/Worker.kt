package com.example.kotlinadvance.poo

class Worker(firstName: String, lastName: String): Person(firstName, lastName), SayHello {
    private var buildType: BuildType = BuildType.OFFICE

    override fun showActivy(): String {
        return "K onda gente $firstName"
    }

    override fun hello() {
        println("Hola mundo")
    }

    companion object{
        const val E = Math.E

    }
}