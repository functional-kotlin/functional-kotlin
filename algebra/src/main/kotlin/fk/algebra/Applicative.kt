package fk.algebra

interface Applicative<A : Any> : Apply<A> {

    // Overloads

    infix fun <B : Any> ap(applicative: Applicative<(A) -> B>): Applicative<B>

    // Overrides

    override fun <B : Any> map(f: (A) -> B): Applicative<B>

    override fun <B : Any> ap(apply: Apply<(A) -> B>): Apply<B>
            = ap(apply as Applicative<(A) -> B>)

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    interface Companion {

        fun <A : Any> of(a: A): Applicative<A>

    }

}
