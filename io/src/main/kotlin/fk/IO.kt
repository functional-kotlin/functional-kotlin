package fk

import fk.algebra.Monad

data class IO<A : Any>(
        val performIO: () -> A) : Monad<A> {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Algebras

    // Functor

    override fun <B : Any> map(f: (A) -> B): IO<B>
            = bind { a -> IO.of(f(a)) }

    // Bind

    fun <B : Any> bind(f: (A) -> IO<B>): IO<B>
            = IO { f(performIO()).performIO() }

    override fun <B : Any> bind(f: (A) -> Monad<B>): Monad<B>
            = bind(f as (A) -> IO<B>)

    // Apply

    fun <B : Any> apply(io: IO<(A) -> B>): IO<B>
            = bind { map(io.performIO()) }

    override fun <B : Any> apply(monad: Monad<(A) -> B>): Monad<B>
            = apply(monad as IO<(A) -> B>)

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    companion object : Monad.Companion {

        override fun <A : Any> of(a: A): IO<A>
                = IO { a }

    }

}
