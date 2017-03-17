package fk.algebra

interface Bind<A : Any> : Apply<A> {

    infix fun <B : Any> bind(f: (A) -> Bind<B>): Bind<B>

    // Overloads

    infix fun <B : Any> ap(bind: Bind<(A) -> B>): Bind<B>

    // Overrides

    override fun <B : Any> map(f: (A) -> B): Bind<B>

    override fun <B : Any> ap(apply: Apply<(A) -> B>): Apply<B>
            = ap(apply as Bind<(A) -> B>)

}
