package fk

import fk.algebra.Apply
import fk.algebra.Bind
import fk.algebra.Monad
import fk.combinator.constant
import fk.combinator.identity

sealed class Maybe<A : Any> : Monad<A> {

    // Catamorpism

    protected abstract fun <B : Any> Maybe<A>.cata(f: (A) -> B, g: () -> B): B

    class None<A : Any> : Maybe<A>() {

        override fun <B : Any> Maybe<A>.cata(f: (A) -> B, g: () -> B) = g()

        override fun equals(other: Any?) = other is None<*>

        override fun hashCode() = 0

    }

    data class Some<A : Any>(val value: A) : Maybe<A>() {

        override fun <B : Any> Maybe<A>.cata(f: (A) -> B, g: () -> B) = f(value)

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
            = cata({ Some(f(it)) }, constant(None<B>()))

    // Apply

    override fun <B : Any> ap(apply: Apply<(A) -> B>): Maybe<B>
            = ap(apply as? Maybe<(A) -> B> ?: throw IllegalArgumentException("Apply must be Maybe"))

    infix fun <B : Any> ap(maybe: Maybe<(A) -> B>): Maybe<B>
            = bind { a -> maybe.map { f -> f(a) } }

    // Bind

    override fun <B : Any> bind(f: (A) -> Bind<B>): Bind<B>
            = bind(f as? (A) -> Maybe<B> ?: throw IllegalArgumentException("Bind must be Maybe"))

    infix fun <B : Any> bind(f: (A) -> Maybe<B>): Maybe<B>
            = cata(f, constant(None<B>()))

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API

    fun <A : Any> Maybe<A>.orElse(value: A): A
            = cata(identity(), constant(value))

    fun <A : Any> Maybe<A>.getOrElse(f: () -> A): A
            = cata(identity(), f)

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    companion object {

        fun <A : Any> of(a: A?): Maybe<A> = if (a == null) Maybe.None() else Maybe.Some(a)

    }

}

