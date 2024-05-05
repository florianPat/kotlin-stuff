package org.example

import kotlinx.coroutines.*

fun main() = runBlocking {
    doWorld()
    println("Done")
}

suspend fun doWorld() = coroutineScope {
    val job = launch {
        delay(3000L)
        println("Hello 1")
    }
    job.join()
    launch {
        delay(2000L)
        println("World 2")
    }
    launch {
        delay(1000L)
        println("World 1")
    }
    println("Hello 2")
}
