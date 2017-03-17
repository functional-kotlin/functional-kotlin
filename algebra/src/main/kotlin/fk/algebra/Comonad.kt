package fk.algebra

interface Comonad<A : Any> : Extend<A> {

    fun extract(): A

    // Overloads

    infix fun <B : Any> extend(comonad: Comonad<(A) -> B>): Comonad<B>

    // Overrides

    override fun <B : Any> map(f: (A) -> B): Comonad<B>

    override fun <B : Any> extend(extend: Extend<(A) -> B>): Extend<B>
            = extend(extend as Comonad<(A) -> B>)

}
