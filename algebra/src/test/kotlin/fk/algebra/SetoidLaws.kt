package fk.algebra

import com.pholser.junit.quickcheck.Property
import com.pholser.junit.quickcheck.When
import fk.Laws.NOT_NULL
import fk.Laws.assertEqual

interface SetoidLaws {

    fun <A : Any> of(): (A) -> Setoid<A>

    @Property
    fun <A : Any> setoidReflexivity(
            @When(satisfies = NOT_NULL) a: A)
            = of<A>()(a) assertEqual of<A>()(a)

    @Property
    fun <A : Any> setoidSymmetry(
            @When(satisfies = NOT_NULL) a: A) {

        val of = of<A>()
        val x = of(a)
        val y = of(a)

        x.equals(y) assertEqual y.equals(x)
    }

    @Property
    fun <A : Any> setoidTransitivity(
            @When(satisfies = NOT_NULL) a: A) {

        val of = of<A>()
        val x = of(a)
        val y = of(a)
        val z = of(a)

        x.equals(y) assertEqual y.equals(z) assertEqual y.equals(z)
    }

}
