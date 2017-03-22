package fk.algebra

interface Bifunctor<A : Any, C : Any> : Functor<A> {

    fun <B : Any, D : Any> bimap(fa: (A) -> B, fb: (C) -> D): Bifunctor<B, D>

    // Overrides

    override fun <B : Any> map(f: (A) -> B): Bifunctor<B, C>

}
