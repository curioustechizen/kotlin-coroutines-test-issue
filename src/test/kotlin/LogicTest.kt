package com.example.coroutines

import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineContext
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class LogicTest {
    private lateinit var viewmodel: ViewModel

    @Mock
    private lateinit var repo: IRepository

    val testCoroutineContext = TestCoroutineContext("cr_test")

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewmodel = ViewModel(repo, testCoroutineContext)

    }

    @Test
    fun `when ViewModel init called then it calls repo init`() {
        viewmodel.init()
        verify(repo).init()
    }

    @Test
    fun `when ViewModel getName called then it calls repo getName`()  {
        viewmodel.getGreeting("foo") {
            println(it)
        }
        runBlocking<Unit>(testCoroutineContext) {
            verify(repo).getGreeting("FOO")
        }
    }
}