package fk.algebra

interface Comonad<A : Any> : Extend<A> {

    fun extract(): A

    // Overloads

    infix fun <B : Any> extend(f: (Comonad<(A)>) -> B): Comonad<B>

    // Overrides

    override fun <B : Any> map(f: (A) -> B): Comonad<B>

    override fun <B : Any> extend(f: (Extend<(A)>) -> B): Extend<B>
            = extend(f as (Comonad<A>) -> B)

}
