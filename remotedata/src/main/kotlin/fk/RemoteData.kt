package fk

import fk.algebra.Apply
import fk.algebra.Bind
import fk.algebra.Monad

sealed class RemoteData<E : Any, A : Any> : Monad<A> {

    // Catamorphism

    protected abstract fun <B : Any> RemoteData<E, A>.cata(f: (A) -> B, g: () -> B): B

    class NotAsked<E : Any, A : Any> : RemoteData<E, A>() {
        override fun <B : Any> RemoteData<E, A>.cata(f: (A) -> B, g: () -> B): B = g()
    }

    class Loading<E : Any, A : Any> : RemoteData<E, A>() {
        override fun <B : Any> RemoteData<E, A>.cata(f: (A) -> B, g: () -> B): B = g()
    }

    data class Failure<E : Any, A : Any>(val error: E) : RemoteData<E, A>() {
        override fun <B : Any> RemoteData<E, A>.cata(f: (A) -> B, g: () -> B): B = g()
    }

    data class Success<E : Any, A : Any>(val value: A) : RemoteData<E, A>() {
        override fun <B : Any> RemoteData<E, A>.cata(f: (A) -> B, g: () -> B): B = f(value)
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Algebras

    // Functor

    override fun <B : Any> map(f: (A) -> B): RemoteData<E, B>
            = bind { a -> Success<E, B>(f(a)) }

    // Apply

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE", "UNCHECKED_CAST")
    override fun <B : Any> ap(remoteData: Apply<(A) -> B>): RemoteData<E, B>
            = ap(remoteData as? RemoteData<E, (A) -> B> ?: throw IllegalArgumentException("Apply must be RemoteData"))

    infix fun <B : Any> ap(remoteData: RemoteData<E, (A) -> B>): RemoteData<E, B>
            = bind { a -> remoteData.map { it(a) } }

    // Bind

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE", "UNCHECKED_CAST")
    override fun <B : Any> bind(remoteData: (A) -> Bind<B>): Bind<B>
            = bind(remoteData as? (A) -> RemoteData<E, B> ?: throw IllegalArgumentException("Bind must be RemoteData"))

    infix fun <B : Any> bind(remoteData: (A) -> RemoteData<E, B>): RemoteData<E, B>
            = cata(remoteData, constant(NotAsked<E, B>()))

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API

    fun <B : Any, C : Any> map2(
            f: (A) -> (B) -> C,
            b: RemoteData<E, B>): RemoteData<E, C> = b ap map(f)

    fun <B : Any, C : Any, D : Any> map3(
            f: (A) -> (B) -> (C) -> D,
            b: RemoteData<E, B>,
            c: RemoteData<E, C>): RemoteData<E, D> = c ap (b ap map(f))

    infix fun orElse(value: A): A
            = cata(identity(), constant(value))

    infix fun getOrElse(f: () -> A): A
            = cata(identity(), f)

    infix fun <B : Any> andMap(wrappedFunction: RemoteData<E, (A) -> B>): RemoteData<E, B> {
        return when (wrappedFunction) {
            is NotAsked -> NotAsked()
            is Loading -> Loading()
            is Failure -> Failure(wrappedFunction.error)
            is Success -> map(wrappedFunction.value)
        }
    }

}
