package fk.algebra

interface Functor<A : Any> {

    infix fun <B : Any> map(f: (A) -> B): Functor<B>

}
