package fk.combinator

infix fun <A, B, C> ((B) -> C).compose(g: (A) -> B): (A) -> C = { a: A -> this(g(a)) }
