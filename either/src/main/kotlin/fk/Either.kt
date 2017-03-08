package fk

import fk.algebra.Apply
import fk.algebra.Bind
import fk.algebra.Monad

sealed class Either<B : Any, A : Any> : Monad<A> {

    // Catamorphism

    protected abstract fun <X : Any> cata(f: (B) -> X, g: (A) -> X): X

    data class Left<B : Any, A : Any>(val value: B) : Either<B, A>() {
        override fun <X : Any> cata(f: (B) -> X, g: (A) -> X) = f(value)
    }

    data class Right<B : Any, A : Any>(val value: A) : Either<B, A>() {
        override fun <X : Any> cata(f: (B) -> X, g: (A) -> X) = g(value)
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Algebras

    // Functor

    override infix fun <C : Any> map(f: (A) -> C): Either<B, C>
            = bind { a -> Right<B, C>(f(a)) }

    // Apply

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE", "UNCHECKED_CAST")
    override fun <C : Any> ap(either: Apply<(A) -> C>): Either<B, C>
            = ap(either as? Either<B, (A) -> C> ?: throw IllegalArgumentException("Apply must be Either"))

    infix fun <C : Any> ap(either: Either<B, (A) -> C>): Either<B, C>
            = bind { a -> either.map { it(a) } }

    // Bind

    @Suppress("UNCHECKED_CAST")
    override fun <C : Any> bind(f: (A) -> Bind<C>): Monad<C>
            = bind(f as? (A) -> Either<B, C> ?: throw IllegalArgumentException("Bind must be Either"))

    infix fun <C : Any> bind(f: (A) -> Either<B, C>): Either<B, C>
            = cata({ Left<B, C>(it) }, f)

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    companion object {

        fun <B : Any, A : Any> of(a: A): Either<B, A> = Either.Right(a)

    }

}
