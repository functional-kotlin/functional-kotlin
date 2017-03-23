package fk.algebra

interface Bind<A : Any> : Apply<A> {

    infix fun <B : Any> bind(f: (A) -> Bind<B>): Bind<B>

    // Overloads

    infix fun <B : Any> apply(bind: Bind<(A) -> B>): Bind<B>

    // Overrides

    override fun <B : Any> map(f: (A) -> B): Bind<B>

    override fun <B : Any> apply(apply: Apply<(A) -> B>): Apply<B>
            = apply(apply as Bind<(A) -> B>)

}
