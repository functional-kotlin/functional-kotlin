package fk

/**
 * W combinator
 */
fun <A : Any, B : Any> duplication(): ((A) -> ((A) -> B)) -> ((A) -> B)
        = { f -> { a -> f(a)(a) } }
