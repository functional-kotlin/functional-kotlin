package fk.algebra

interface Semigroup<A : Any> {

    infix fun concat(other: Semigroup<A>): Semigroup<A>

    // Overloads

    infix operator fun plus(other: Semigroup<A>): Semigroup<A>
            = concat(other)

}
