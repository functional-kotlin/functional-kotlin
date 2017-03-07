package fk.algebra

interface Bind<A : Any> : Apply<A> {

    infix fun <B : Any> bind(bind: (A) -> Bind<B>): Bind<B>

}
