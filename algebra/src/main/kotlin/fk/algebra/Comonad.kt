package fk.algebra

interface Comonad<A : Any> : Extend<A> {

    fun extract(): A

    override infix fun <B : Any> map(f: (A) -> B): Comonad<B>

    override infix fun <B : Any> extend(extend: Extend<(A) -> B>): Comonad<B>

}
