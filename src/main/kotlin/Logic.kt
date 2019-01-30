package com.example.coroutines

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

interface IRepository{
    fun init()
    suspend fun getGreeting(name: String): String
}

class Repository: IRepository {
    override fun init() {
        //Do some init here
    }

    override suspend fun getGreeting(name: String): String {
        log("Inside repo.getGreeting() before delay")
        delay(400)
        log("Inside repo.getGreeting() after delay")
        return "Hello $name!"
    }

}

class ViewModel(val repo: IRepository, baseCoroutineContext: CoroutineContext): CoroutineScope {

    override val coroutineContext = baseCoroutineContext + Job()

    fun init() {
        repo.init()
    }

    fun getGreeting(name: String, callback: (String) -> Unit) {
        log("Inside viewModel.getGreeting()")
        launch {
            val name = repo.getGreeting(name.toUpperCase())
            callback.invoke(name)
        }
    }

    fun close() {
        coroutineContext.cancel()
    }
}