package fk.algebra

interface Alternative<A : Any> : Plus<A>, Applicative<A> {

    // Overloads

    infix fun alt(alternative: Alternative<A>): Alternative<A>

    infix fun <B : Any> ap(alternative: Alternative<(A) -> B>): Alternative<B>

    // Overrides

    override fun <B : Any> map(f: (A) -> B): Alternative<B>

    override fun alt(alt: Alt<A>): Alt<A>
            = alt(alt as Alternative<A>)

    override fun <B : Any> ap(apply: Apply<(A) -> B>): Apply<B>
            = ap(apply as Alternative<(A) -> B>)

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    interface Companion {

        fun <A : Any> zero(): Alternative<A>

        fun <A : Any> of(a: A): Alternative<A>

    }

}
