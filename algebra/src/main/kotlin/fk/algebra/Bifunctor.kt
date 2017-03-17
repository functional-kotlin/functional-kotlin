package fk.algebra

interface Bifunctor<A : Any, B : Any> : Functor<A> {

    fun <C : Any, D : Any> bimap(fa: (A) -> C, fb: (B) -> D): Bifunctor<C, D>

    // Overrides

    override fun <C : Any> map(f: (A) -> C): Bifunctor<C, B>

}
