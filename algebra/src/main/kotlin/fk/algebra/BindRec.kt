package fk.algebra

interface BindRec<A : Any> : Bind<A> {

    override infix fun <B : Any> map(f: (A) -> B): BindRec<B>

    override infix fun <B : Any> ap(apply: Apply<(A) -> B>): BindRec<B>

    override infix fun <B : Any> bind(f: (A) -> Bind<B>): BindRec<B>

}
