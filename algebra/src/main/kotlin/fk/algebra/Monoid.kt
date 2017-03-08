package fk.algebra

interface Monoid<A : Any> : Semigroup<A> {

    override infix fun concat(other: Semigroup<A>): Monoid<A>

}
