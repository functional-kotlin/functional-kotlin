package fk.algebra

interface Traversable<A : Any> : Functor<A>, Foldable<A> {

    fun <B : Any> traverse(type: Class<A>, f: (A) -> Applicative<B>): Applicative<Traversable<B>>

    // Overrides

    override fun <B : Any> map(f: (A) -> B): Traversable<B>

}
