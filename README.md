## Objective

Try to write unit tests for a kotlin class that includes suspending functions. In particular, 
> call a _regular_ function and then verify that it in turn calls a _suspending_ function.

This is a regular desktop Java project that you can run with IntelliJ IDEA

## Explanation

In this project, `ViewModel` has a `getGreeting()` function which in turn calls a suspending `getGreeting()` on the `Repository`. This suspending function simply calls `delay()` but in real world usage it might do some computation or I/O.

The test that I'm trying to get to work is `when ViewModel getGreeting called then it calls repo getGreeting` in [LogicTest.kt](src/test/kotlin/LogicTest.kt).

## The key

I was having problems where Mockito verification was failing - I would see an error saying that the repository `getName()` suspending function was never called.

The key to fixing it was to share the coroutineContext between `runBlocking` in my tests and my `ViewModel`.

This fails:
```kotlin
runBlocking<Unit> {
    verify(repo).getGreeting("")
}
```

This passes:
```kotlin
runBlocking<Unit>(testCoroutineContext) {
    verify(repo).getGreeting("")
}
```

See the LogicTest.kt file to see how this context is shared between the test code and the ViewModel