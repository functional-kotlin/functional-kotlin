package fk.algebra

object ApplyLaws {

    /**
     * `v.ap(u.ap(a.map(f => g => x => f(g(x)))))` is equivalent to `v.ap(u).ap(a)`
     */
    fun <X : (Any) -> Any> composition(of: (X) -> Apply<X>, x: X) {
//        val y = of(x)
//
//        val a = y.ap(y.ap(y.map(compose)))
//        val b = y.ap(y).ap(y)
//
//        assertThat(a).isEqualTo(b)
    }

}
