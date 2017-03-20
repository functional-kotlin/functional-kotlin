package fk

/**
 * I combinator
 */
fun <A : Any> identity(): (A) -> A
        = { a -> a }
