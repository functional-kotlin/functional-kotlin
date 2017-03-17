package fk

fun <A : Any> identity(): (A) -> A
        = { a: A -> a }
