package fk

import fk.algebra.Monad

sealed class Either<B : Any, A : Any> : Monad<A> {

    // Catamorphism

    abstract fun <X : Any> cata(f: (B) -> X, g: (A) -> X): X

    data class Left<B : Any, A : Any>(val value: B) : Either<B, A>() {
        override fun <X : Any> cata(f: (B) -> X, g: (A) -> X) = f(value)
    }

    data class Right<B : Any, A : Any>(val value: A) : Either<B, A>() {
        override fun <X : Any> cata(f: (B) -> X, g: (A) -> X) = g(value)
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Algebras

    // Functor

    override fun <C : Any> map(f: (A) -> C): Either<B, C>
            = bind { a -> Right<B, C>(f(a)) }

    // Apply

    infix fun <C : Any> apply(either: Either<B, (A) -> C>): Either<B, C>
            = bind { a -> either.map { f -> f(a) } }

    override fun <C : Any> apply(monad: Monad<(A) -> C>): Monad<C>
            = apply(monad as Either<B, (A) -> C>)

    // Bind

    infix fun <C : Any> bind(f: (A) -> Either<B, C>): Either<B, C>
            = cata({ b -> Left<B, C>(b) }, f)

    override fun <C : Any> bind(f: (A) -> Monad<C>): Monad<C>
            = bind(f as (A) -> Either<B, C>)

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API

    // Mapping

    fun <D : Any> mapLeft(f: (B) -> D): Either<D, A>
            = cata({ b -> Left<D, A>(f(b)) }, { a -> Right<D, A>(a) })

    fun <C : Any> mapRight(f: (A) -> C): Either<B, C>
            = map(f)

    fun <C : Any, D : Any> mapBoth(fB: (B) -> D, fA: (A) -> C): Either<D, C>
            = cata({ b -> Left<D, C>(fB(b)) }, { a -> Right<D, C>(fA(a)) })

    // Binding

    fun <D : Any> bindLeft(f: (B) -> Either<D, A>): Either<D, A>
            = cata(f, { a -> Right<D, A>(a) })

    fun <C : Any> bindRight(f: (A) -> Either<B, C>): Either<B, C>
            = bind(f)

    fun <C : Any, D : Any> bindBoth(fB: (B) -> Either<D, C>, fA: (A) -> Either<D, C>): Either<D, C>
            = cata(fB, fA)

    // Unwraping

    fun fromLeft(default: () -> B): B
            = cata({ it }, { default() })

    fun fromRight(default: () -> A): A
            = cata({ default() }, { it })

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    companion object : Monad.Companion {

        override fun <A : Any> of(a: A): Either<Any, A>
                = Either.Right<Any, A>(a)

    }

}
