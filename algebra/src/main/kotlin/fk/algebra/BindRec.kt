package fk.algebra

interface BindRec<A : Any> : Bind<A> {

    // Overloads

    infix fun <B : Any> ap(bindRec: BindRec<(A) -> B>): BindRec<B>

    infix fun <B : Any> bind(f: (A) -> BindRec<B>): BindRec<B>

    // Overrides

    override fun <B : Any> map(f: (A) -> B): BindRec<B>

    override fun <B : Any> ap(apply: Apply<(A) -> B>): Apply<B>
            = ap(apply as BindRec<(A) -> B>)

    override fun <B : Any> bind(f: (A) -> Bind<B>): Bind<B>
            = bind(f as (A) -> BindRec<B>)

}
