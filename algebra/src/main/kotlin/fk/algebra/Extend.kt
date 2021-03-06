package fk.algebra

interface Extend<A : Any> : Functor<A> {

    infix fun <B : Any> extend(f: (Extend<(A)>) -> B): Extend<B>

    // Overrides

    override fun <B : Any> map(f: (A) -> B): Extend<B>

}
