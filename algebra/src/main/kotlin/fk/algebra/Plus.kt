package fk.algebra

interface Plus<A : Any> : Alt<A> {

    override infix fun <B : Any> map(f: (A) -> B): Plus<B>

    override infix fun alt(alt: Alt<A>): Plus<A>

}
