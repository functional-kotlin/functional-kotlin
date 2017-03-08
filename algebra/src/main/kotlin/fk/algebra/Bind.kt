package fk.algebra

interface Bind<A : Any> : Apply<A> {

    infix fun <B : Any> bind(f: (A) -> Bind<B>): Bind<B>

    override infix fun <B : Any> map(f: (A) -> B): Bind<B>

    override infix fun <B : Any> ap(apply: Apply<(A) -> B>): Bind<B>

}
