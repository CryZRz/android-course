package com.cursosant.jcdatastore

import kotlinx.coroutines.delay
import kotlin.random.Random

suspend fun simulateDelay() {
    delay(Random.nextLong(300, 1_500))
}