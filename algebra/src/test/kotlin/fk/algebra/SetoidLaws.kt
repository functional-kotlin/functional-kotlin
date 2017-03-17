package fk.algebra

import com.pholser.junit.quickcheck.Property
import com.pholser.junit.quickcheck.When
import fk.Properties.NOT_NULL

interface SetoidLaws {

    fun <A : Any> of(): (A) -> Setoid<A>

    @Property
    fun <A : Any> setoidReflexivity(
            @When(satisfies = NOT_NULL) a: A) {

        val of = of<A>()
        val x = of(a)

        assert(
                x.equals(x)
        )
    }

    @Property
    fun <A : Any> setoidSymmetry(
            @When(satisfies = NOT_NULL) a: A) {

        val of = of<A>()
        val x = of(a)
        val y = of(a)

        assert(
                x.equals(y) == y.equals(x)
        )
    }

    @Property
    fun <A : Any> setoidTransitivity(
            @When(satisfies = NOT_NULL) a: A) {

        val of = of<A>()
        val x = of(a)
        val y = of(a)
        val z = of(a)

        assert(
                x.equals(y) == y.equals(z) == y.equals(z)
        )
    }

}
