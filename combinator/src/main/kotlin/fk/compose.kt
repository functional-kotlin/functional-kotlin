package fk

infix fun <A : Any, B : Any, C : Any> ((B) -> C).forwardCompose(f: (C) -> A): (B) -> A
        = andThen(f)

infix fun <A : Any, B : Any, C : Any> ((B) -> C).andThen(f: (C) -> A): (B) -> A
        = andThen<A, B, C>()(this, f)

infix fun <A : Any, B : Any, C : Any> ((B) -> C).compose(f: (A) -> B): (A) -> C
        = compose<A, B, C>()(this, f)

fun <A : Any, B : Any, C : Any> andThen(): ((B) -> C, (C) -> A) -> ((B) -> A)
        = { f: (B) -> C, g: (C) -> A -> { b: B -> g(f(b)) } }

fun <A : Any, B : Any, C : Any> compose(): ((B) -> C, (A) -> B) -> ((A) -> C)
        = { f: (B) -> C, g: (A) -> B -> { a: A -> f(g(a)) } }
