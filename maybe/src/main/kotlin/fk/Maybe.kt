package fk

import fk.algebra.Apply
import fk.algebra.Bind
import fk.algebra.Monad

sealed class Maybe<A : Any> : Monad<A> {

    // Catamorphism

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

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE", "UNCHECKED_CAST")
    override fun <B : Any> ap(maybe: Apply<(A) -> B>): Maybe<B>
            = ap(maybe as? Maybe<(A) -> B> ?: throw IllegalArgumentException("Apply must be Maybe"))

    infix fun <B : Any> ap(maybe: Maybe<(A) -> B>): Maybe<B>
            = bind { a -> maybe.map { f -> f(a) } }

    // Bind

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE", "UNCHECKED_CAST")
    override fun <B : Any> bind(maybe: (A) -> Bind<B>): Bind<B>
            = bind(maybe as? (A) -> Maybe<B> ?: throw IllegalArgumentException("Bind must be Maybe"))

    infix fun <B : Any> bind(maybe: (A) -> Maybe<B>): Maybe<B>
            = cata(maybe, constant(None<B>()))

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API

    infix fun orElse(value: A): A
            = cata(identity(), constant(value))

    infix fun getOrElse(f: () -> A): A
            = cata(identity(), f)

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    companion object {

        fun <A : Any> of(a: A?): Maybe<A> = if (a == null) Maybe.None() else Maybe.Some(a)

    }

}

