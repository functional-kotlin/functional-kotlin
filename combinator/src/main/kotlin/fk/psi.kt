package fk

/**
 * P combinator
 */
fun <A : Any, B : Any, C : Any> psi(): ((B) -> ((B) -> C)) -> (((A) -> B) -> ((A) -> ((A) -> C)))
        = { f -> { g -> { x -> { y -> f(g(x))(g(y)) } } } }
