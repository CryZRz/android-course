package com.example.kotlinadvance.poo

import android.location.Address

data class Building(var name: String, val address: String, var number: Int = 477894563) {
    var person: String
    private var cameras: Int = 0
    private val passwrod: String = "1234"

    init {
        person = ""
    }

    constructor(person: String, name: String, address: String) : this(name, address) {
        this.person = person
    }

    override fun toString(): String {
        return "Nombre $name Direccion $address number $number persona $person"
    }

    fun saludar(){
        println("Hola ${this.name}")
    }

    fun getCamera(password: String): Int {
        if (this.passwrod == password){
            return this.cameras
        }else{
            return -1
        }
    }

    fun setCamera(password: String, cameras: Int) {
        if (this.passwrod == password){
            this.cameras = cameras
        }
    }
}