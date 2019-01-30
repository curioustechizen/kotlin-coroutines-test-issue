package com.example.coroutines

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

interface IRepository{
    fun init()
    suspend fun getName(): String
}

class Repository: IRepository {
    override fun init() {
        //Do some init here
    }

    override suspend fun getName(): String {
        log("Inside repo.getName() before delay")
        delay(400)
        log("Inside repo.getName() after delay")
        return "World!"
    }

}

class ViewModel(val repo: IRepository, baseCoroutineContext: CoroutineContext): CoroutineScope {

    override val coroutineContext = baseCoroutineContext + Job()

    fun init() {
        repo.init()
    }

    fun getName(callback: (String) -> Unit) {
        log("Inside viewModel.getName()")
        launch {
            val name = repo.getName()
            callback.invoke(name)
        }
    }

    fun close() {
        coroutineContext.cancel()
    }
}