package fk.algebra

interface Apply<A : Any> : Functor<A> {

    infix fun <B : Any> ap(apply: Apply<(A) -> B>): Apply<B>

    override infix fun <B : Any> map(f: (A) -> B): Apply<B>

}
