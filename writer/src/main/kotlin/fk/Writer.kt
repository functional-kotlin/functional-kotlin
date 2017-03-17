package fk

import fk.algebra.Monad
import fk.algebra.Monoid

class Writer<W : Monoid<*>, A : Any>(
        val runWriter: () -> Pair<A, W>) : Monad<A> {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Algebra

    // Map

    override fun <B : Any> map(f: (A) -> B): Writer<W, B> = Writer {
        val result = run()
        Pair(f(result.first), result.second)
    }

    // Apply

    fun <B : Any> ap(writer: Writer<W, (A) -> B>): Writer<W, B>
            = bind { a -> writer.map { f -> f(a) } }

    @Suppress("UNCHECKED_CAST")
    override fun <B : Any> ap(monad: Monad<(A) -> B>): Monad<B>
            = ap(monad as Writer<W, (A) -> B>)

    // Bind

    fun <B : Any> bind(f: (A) -> Writer<W, B>): Writer<W, B> {
        return Writer<W, B> {
            val result = run()
            val t = f(result.first).run()
            Pair(t.first, (result.second + t.second as Nothing)) as Pair<B, W>
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <B : Any> bind(f: (A) -> Monad<B>): Monad<B>
            = bind(f as (A) -> Writer<W, B>)

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API

    fun run(): Pair<A, W> = runWriter()

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    companion object {

        //fun <W : Monoid<*>, A : Any> of(a: A): Writer<W, A> = Writer { Pair(a, Monoid.zero<W>()) }

    }

}
