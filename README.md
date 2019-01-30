## Objective

Try to write unit tests for a kotlin class that includes suspending functions. In particular, 
> call a _regular_ function and then verify that it in turn calls a _suspending_ function.

I have **not** managed to get such a test to work yet.

This is a regular desktop Java project that you can run with IntelliJ IDEA

## Explanation

In this project, `ViewModel` has a `getName()` function which in turn calls a suspending `getName()` on the `Repository`. This suspending function simply calls `delay()` but in real world usage it might do some computation or I/O.

The test that I'm trying to get to work is `when ViewModel getName called then it calls repo getName` in [LogicTest.kt](src/test/kotlin/LogicTest.kt).

## The problem

Mockito verification fails - I see an error saying that the repository `getName()` suspending function was never called.