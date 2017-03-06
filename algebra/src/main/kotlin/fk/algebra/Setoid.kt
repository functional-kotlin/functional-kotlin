package fk.algebra

interface Setoid<A : Any> {

    infix fun equals(other: Setoid<A>): Boolean

}
