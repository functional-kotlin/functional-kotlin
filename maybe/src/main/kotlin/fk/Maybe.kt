package fk

import fk.algebra.Applicative
import fk.algebra.Monad

sealed class Maybe<A : Any> : Monad<A> {

    // Catamorphism

    protected abstract fun <B : Any> cata(f: (A) -> B, g: () -> B): B

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Types

    data class Some<A : Any>(val value: A) : Maybe<A>() {

        override fun <B : Any> cata(f: (A) -> B, g: () -> B) = f(value)

    }

    class None<A : Any> : Maybe<A>() {

        override fun <B : Any> cata(f: (A) -> B, g: () -> B) = g()

        override fun equals(other: Any?) = other is None<*>

        override fun hashCode() = 0

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Algebras

    // Functor

    /**
     * Transforms a Maybe<A> to a Maybe<B>
     *
     * @param f A function which takes an A and returns a B
     */
    override fun <B : Any> map(f: (A) -> B): Maybe<B>
            = cata({ a -> Some(f(a)) }, { None<B>() })

    // Apply

    infix fun <B : Any> ap(maybe: Maybe<(A) -> B>): Maybe<B>
            = bind { a -> maybe.map { f -> f(a) } }

    override fun <B : Any> ap(monad: Monad<(A) -> B>): Monad<B>
            = ap(monad as Maybe<(A) -> B>)

    // Bind


    infix fun <B : Any> bind(maybe: (A) -> Maybe<B>): Maybe<B>
            = cata(maybe, { None<B>() })

    override fun <B : Any> bind(f: (A) -> Monad<B>): Monad<B>
            = bind(f as (A) -> Maybe<B>)

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API

    fun get(): A? = when (this) {
        is Maybe.Some -> value
        is Maybe.None -> null
    }

    infix fun getOrElse(value: A): A
            = getOrElse { value }

    infix fun getOrElse(f: () -> A): A
            = cata(identity(), f)

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    companion object : Applicative.Companion {

        override fun <A : Any> of(a: A): Maybe<A>
                = Maybe.Some(a)

    }

}

