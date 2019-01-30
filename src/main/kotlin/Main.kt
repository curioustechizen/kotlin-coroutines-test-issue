package com.example.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

fun main() = runBlocking<Unit> {
    val viewModel = ViewModel(Repository(), Dispatchers.Default)
    viewModel.init()
    viewModel.getGreeting("world") {
        log("$it")
    }
    Thread.sleep(2000)
    log("Done!")
}