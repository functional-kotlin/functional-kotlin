package fk.algebra

interface Monad<A : Any> : Applicative<A>, Bind<A> {

    override infix fun <B : Any> map(f: (A) -> B): Monad<B>

    override infix fun <B : Any> ap(apply: Apply<(A) -> B>): Monad<B>

    override infix fun <B : Any> bind(f: (A) -> Bind<B>): Monad<B>

}
