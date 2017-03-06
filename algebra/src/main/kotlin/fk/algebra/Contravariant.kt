package fk.algebra

interface Contravariant<A : Any> {

    infix fun <B : Any> contramap(f: (B) -> A): Contravariant<B>

}
