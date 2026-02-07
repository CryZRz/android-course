package com.example.corutinas

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

val limit = 10_000

fun main(){
    //threads()
    //coroutines()
    //job()
    scope()
    readln()
}

fun scope(){
    newTopic("Scope")
    val job = Job()
    val scope = CoroutineScope(job)
    scope.launch {
        println("isActive: ${job.isActive}")
        println("isCancelled: ${job.isCancelled}")
        println("isComplete: ${job.isCompleted}")

        (1..limit).forEach {

            if (!scope.isActive) return@launch

            println(it)
            if (it == 3){
                scope.cancel()

                println("¡Cancelado!")
                println("isActive: ${job.isActive}")
                println("isCancelled: ${job.isCancelled}")
                println("isComplete: ${job.isCompleted}")
            }
        }
    }

    runBlocking {
        delay(someTime())
        println("¡Cancelado!")
        println("isActive: ${job.isActive}")
        println("isCancelled: ${job.isCancelled}")
        println("isComplete: ${job.isCompleted}")
    }
}

fun job(){
    newTopic("Job")
    runBlocking {
        val time = someTime()
        val job = launch {
            println("Incia proceso... ")
            delay(time)
            print("Termina proceso... ")
        }
        println("isActive: ${job.isActive}")
        println("isCancelled: ${job.isCancelled}")
        println("isComplete: ${job.isCompleted}")

        job.cancel()
        delay(time*2)
        println("isActive: ${job.isActive}")
        println("isCancelled: ${job.isCancelled}")
        println("isComplete: ${job.isCompleted}")
    }
}

fun threads() {
    newTopic("Threads")
    val start = System.currentTimeMillis()
    (1..limit).forEach {
        Thread{
            print("*")
            if (it == limit){
                val end = System.currentTimeMillis()
                val time = (end-start)/1000
                println("\nTiempo: $time segundos")
            }
            Thread.sleep(someTime())
        }.start()
    }
}

fun coroutines() {
    newTopic("Coroutines")
    val start = System.currentTimeMillis()
    runBlocking {
        (1..limit).forEach {
            launch {
                print("*")
                if (it == limit){
                    val end = System.currentTimeMillis()
                    val time = (end-start)/1000
                    println("\nTiempo: $time segundos")
                }
                delayRandom()
            }
        }
    }
}

fun suspendFun(){
    newTopic("Corrutinas")
    println("Time: ${someTime()}")
    runBlocking {
        println("Delay: ${delayRandom()}")
    }
}

suspend fun delayRandom(){
    delay(someTime())
}

fun someTime(): Long = 500

fun newTopic(topic: String){
    val separator = "================================="
    println("\n $separator $topic $separator")
}