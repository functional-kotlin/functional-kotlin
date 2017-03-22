package fk.algebra

interface Semigroup<A : Any> {

    infix fun concat(other: Semigroup<A>): Semigroup<A>

}
