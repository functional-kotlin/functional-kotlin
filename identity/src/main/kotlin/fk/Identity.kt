package fk

import fk.algebra.Monad

data class Identity<A : Any>(
        val value: A) : Monad<A> {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Algebras

    // Functor

    override fun <B : Any> map(f: (A) -> B): Identity<B>
            = bind { a -> Identity.of(f(a)) }

    // Bind

    fun <B : Any> bind(f: (A) -> Identity<B>): Identity<B>
            = f(value)

    override fun <B : Any> bind(f: (A) -> Monad<B>): Monad<B>
            = bind(f as (A) -> Identity<B>)

    // Apply

    fun <B : Any> apply(identity: Identity<(A) -> B>): Identity<B>
            = bind { map(identity.value) }

    override fun <B : Any> apply(monad: Monad<(A) -> B>): Monad<B>
            = apply(monad as Identity<(A) -> B>)

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    companion object : Monad.Companion {

        override fun <A : Any> of(a: A): Identity<A>
                = Identity(a)

    }

}
