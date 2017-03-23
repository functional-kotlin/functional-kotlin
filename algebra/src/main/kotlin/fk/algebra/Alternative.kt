package fk.algebra

interface Alternative<A : Any> : Plus<A>, Applicative<A>, Monoid<A> {

    // Overloads

    infix fun alt(alternative: Alternative<A>): Alternative<A>

    infix fun <B : Any> apply(alternative: Alternative<(A) -> B>): Alternative<B>

    // Overrides

    override fun <B : Any> map(f: (A) -> B): Alternative<B>

    override fun alt(alt: Alt<A>): Alt<A>
            = alt(alt as Alternative<A>)

    override fun <B : Any> apply(apply: Apply<(A) -> B>): Apply<B>
            = apply(apply as Alternative<(A) -> B>)

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    interface Companion : Plus.Companion, Monoid.Companion {

        fun <A : Any> of(a: A): Alternative<A>

        override fun <A : Any> zero(): Alternative<A>

        override fun <A : Any> empty(): Alternative<A>

    }

}
