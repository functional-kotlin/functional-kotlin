package fk.algebra

interface Alt<A : Any> : Functor<A> {

    infix fun alt(alt: Alt<A>): Alt<A>

    // Overrides

    override fun <B : Any> map(f: (A) -> B): Alt<B>

}
