package fk.algebra

interface Comonad<A : Any> : Extend<A> {

    fun extract(): A

}
