package fk.algebra

interface Profunctor<B : Any, C : Any> : Functor<B> {

    fun <A : Any, D : Any> promap(fa: (A) -> B, fb: (C) -> D): Profunctor<A, D>

    // Overrides

    override fun <A : Any> map(f: (B) -> A): Profunctor<A, C>

}
