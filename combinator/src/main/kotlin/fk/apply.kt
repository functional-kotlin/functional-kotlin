package fk

/**
 * A combinator
 */
fun <A : Any, B : Any> apply(): ((A) -> B) -> ((A) -> B)
        = { f -> { b -> f(b) } }
