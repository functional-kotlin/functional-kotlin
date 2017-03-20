package fk

/**
 * T combinator
 */
fun <A : Any, B : Any> thrush(): (A) -> (((A) -> B) -> B)
        = { x -> { f -> f(x) } }
