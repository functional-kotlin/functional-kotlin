package fk

/**
 * C combinator
 */
fun <A : Any, B : Any, C : Any> flip(): ((A) -> (B) -> C) -> ((B) -> (A) -> C)
        = { f -> { y -> { x -> f(x)(y) } } }
