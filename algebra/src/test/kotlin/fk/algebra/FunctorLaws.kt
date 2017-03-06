package fk.algebra

import com.google.common.truth.Truth.assertThat

object FunctorLaws {

    /**
     * `u.map { a -> a }` is equivalent to `u`
     */
    fun <X : Any> identity(of: (X) -> Functor<X>, x: X) {
        val a = of(x).map { x -> x }
        val b = of(x)

        assertThat(a).isEqualTo(b)
    }

    /**
     * `u.map { x -> f(g(x)) }` is equivalent to `u.map(g).map(f)`
     */
    fun <X : Any, Y : Any, Z : Any> composition(of: (X) -> Functor<X>, x: X, f: (X) -> Y, g: (Y) -> Z) {
        val a = of(x).map { x -> g(f(x)) }
        val b = of(x).map(f).map(g)

        assertThat(a).isEqualTo(b)
    }

}
