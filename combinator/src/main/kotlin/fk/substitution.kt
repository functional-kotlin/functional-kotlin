package fk

/**
 * S combinator
 */
fun <A : Any, B : Any, C : Any> substitution(): ((A) -> ((B) -> C)) -> (((A) -> B) -> ((A) -> C))
        = { f -> { g -> { x -> f(x)(g(x)) } } }
