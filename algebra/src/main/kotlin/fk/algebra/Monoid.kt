package fk.algebra

interface Monoid<A : Any> : Semigroup<A> {

    // Overloads

    infix fun concat(other: Monoid<A>): Monoid<A>

    // Overrides

    override fun concat(other: Semigroup<A>): Semigroup<A>
            = concat(other as Monoid<A>)

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    interface Companion {

        fun <A : Any> empty(): Monoid<A>

    }

}
