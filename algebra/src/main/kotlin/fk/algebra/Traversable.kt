package fk.algebra

interface Traversable<A : Any> : Functor<A>, Foldable<A> {

    override infix fun <B : Any> map(f: (A) -> B): Traversable<B>

    override fun <B : Any> reduce(initial: B, f: (B, A) -> B): Traversable<B>

}
