package fk

import fk.algebra.Monad

sealed class RemoteData<E : Any, A : Any> : Monad<A> {

    // Catamorphism

    protected abstract fun <B : Any> RemoteData<E, A>.cata(
            notAsked: () -> B,
            loading: () -> B,
            failure: (E) -> B,
            success: (A) -> B): B

    class NotAsked<E : Any, A : Any> : RemoteData<E, A>() {

        override fun <B : Any> RemoteData<E, A>.cata(
                notAsked: () -> B,
                loading: () -> B,
                failure: (E) -> B,
                success: (A) -> B): B = notAsked()

    }

    class Loading<E : Any, A : Any> : RemoteData<E, A>() {

        override fun <B : Any> RemoteData<E, A>.cata(
                notAsked: () -> B,
                loading: () -> B,
                failure: (E) -> B,
                success: (A) -> B): B = loading()

    }

    data class Failure<E : Any, A : Any>(val error: E) : RemoteData<E, A>() {

        override fun <B : Any> RemoteData<E, A>.cata(
                notAsked: () -> B,
                loading: () -> B,
                failure: (E) -> B,
                success: (A) -> B): B = failure(error)

    }

    data class Success<E : Any, A : Any>(val value: A) : RemoteData<E, A>() {

        override fun <B : Any> RemoteData<E, A>.cata(
                notAsked: () -> B,
                loading: () -> B,
                failure: (E) -> B,
                success: (A) -> B): B = success(value)

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Algebras

    // Functor

    override fun <B : Any> map(f: (A) -> B): RemoteData<E, B>
            = bind { a -> Success<E, B>(f(a)) }

    // Apply

    infix fun <B : Any> ap(remoteData: RemoteData<E, (A) -> B>): RemoteData<E, B>
            = bind { a -> remoteData.map { it(a) } }

    override fun <B : Any> ap(monad: Monad<(A) -> B>): Monad<B>
            = ap(monad as RemoteData<E, (A) -> B>)

    // Bind

    infix fun <B : Any> bind(f: (A) -> RemoteData<E, B>): RemoteData<E, B>
            = cata({ NotAsked<E, B>() }, { Loading<E, B>() }, { Failure<E, B>(it) }, f)

    override fun <B : Any> bind(f: (A) -> Monad<B>): Monad<B>
            = bind(f as (A) -> RemoteData<E, B>)

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API

    fun <B : Any, C : Any> map2(
            f: (A) -> (B) -> C,
            b: RemoteData<E, B>): RemoteData<E, C> = b ap map(f)

    fun <B : Any, C : Any, D : Any> map3(
            f: (A) -> (B) -> (C) -> D,
            b: RemoteData<E, B>,
            c: RemoteData<E, C>): RemoteData<E, D> = c ap (b ap map(f))

    infix fun getOrElse(value: A): A
            = cata({ value }, { value }, { value }, identity())

    infix fun getOrElse(f: () -> A): A
            = cata(f, f, { f() }, identity())

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    companion object : Monad.Companion {

        override fun <A : Any> of(a: A): RemoteData<Any, A>
                = RemoteData.Success<Any, A>(a)

    }

}
