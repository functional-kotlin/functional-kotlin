package fk.algebra

interface Profunctor<A : Any, B : Any> : Functor<A> {

    fun <C : Any, D : Any> promap(fa: (C) -> A, fb: (B) -> D): Profunctor<C, D>

    override infix fun <C : Any> map(f: (A) -> C): Profunctor<C, B>

}
