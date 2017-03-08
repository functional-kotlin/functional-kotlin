package fk.algebra

interface Extend<A : Any> : Functor<A> {

    infix fun <B : Any> extend(extend: Extend<(A) -> B>): Extend<B>

    override infix fun <B : Any> map(f: (A) -> B): Extend<B>

}
