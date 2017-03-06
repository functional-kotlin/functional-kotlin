package fk.algebra

import com.google.common.truth.Truth

object BindLaws {

    /**
     * `m.bind(f).bind(g)` is equivalent to `m.bind { x -> f(x).bind(g) }` (associativity)
     */
    fun <X : Any> associativity(of: (X) -> Bind<X>, x: X) {
        val a = of(x).bind(of).bind(of)
        val b = of(x).bind { x -> of(x).bind(of) }

        Truth.assertThat(a).isEqualTo(b)
    }

}
