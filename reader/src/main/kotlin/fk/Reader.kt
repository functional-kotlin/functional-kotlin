package fk

import fk.algebra.Monad

class Reader<R : Any, A : Any>(
        private val runReader: (R) -> A) : Monad<A> {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Algebra

    // Functor

    override fun <B : Any> map(f: (A) -> B): Reader<R, B>
            = bind { a -> Reader.of<R, B>(f(a)) }

    // Apply

    fun <B : Any> ap(reader: Reader<R, (A) -> B>): Reader<R, B>
            = bind { a -> reader.map { f -> f(a) } }

    @Suppress("UNCHECKED_CAST")
    override fun <B : Any> ap(monad: Monad<(A) -> B>): Monad<B>
            = ap(monad as Reader<R, (A) -> B>)

    // Bind

    fun <B : Any> bind(f: (A) -> Reader<R, B>): Reader<R, B>
            = Reader { r -> f(run(r)).run(r) }

    @Suppress("UNCHECKED_CAST")
    override fun <B : Any> bind(f: (A) -> Monad<B>): Monad<B>
            = bind(f as (A) -> Reader<R, B>)

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API

    fun run(reader: R): A = runReader(reader)

    fun ask() = Reader(identity<R>())

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    companion object {

        fun <R : Any, A : Any> of(a: A): Reader<R, A> = Reader { a }

    }

}
