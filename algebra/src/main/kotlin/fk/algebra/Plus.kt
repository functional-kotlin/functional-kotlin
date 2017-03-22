package fk.algebra

interface Plus<A : Any> : Alt<A> {

    // Overloads

    infix fun alt(plus: Plus<A>): Plus<A>

    // Overrides

    override fun <B : Any> map(f: (A) -> B): Plus<B>

    override fun alt(alt: Alt<A>): Alt<A>
            = alt(alt as Plus<A>)

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    interface Companion {

        fun <A : Any> zero(): Plus<A>

    }

}
