package fk

/**
 * K combinator
 */
fun <A : Any, B : Any> constant(): (A) -> ((B) -> A)
        = { a -> { b -> a } }
