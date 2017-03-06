package fk.combinator

fun <A : Any> identity() = { a: A -> a }
