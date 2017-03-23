package fk.algebra

interface Apply<A : Any> : Functor<A> {

    infix fun <B : Any> apply(apply: Apply<(A) -> B>): Apply<B>

    // Overrides

    override fun <B : Any> map(f: (A) -> B): Apply<B>

}
