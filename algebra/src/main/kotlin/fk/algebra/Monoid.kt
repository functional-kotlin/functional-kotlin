package fk.algebra

interface Monoid<A : Any> : Semigroup<A> {

    // Overloads

    infix fun concat(other: Monoid<A>): Monoid<A>

    infix operator fun plus(other: Monoid<A>): Monoid<A>
            = concat(other)

    // Overrides

    override fun concat(other: Semigroup<A>): Semigroup<A>
            = concat(other as Monoid<A>)

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    interface Companion {

        fun <A : Any> empty(): Monoid<A>

    }

}
