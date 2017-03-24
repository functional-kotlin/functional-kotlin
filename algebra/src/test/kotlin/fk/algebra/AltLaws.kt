package fk.algebra

import com.pholser.junit.quickcheck.Property
import com.pholser.junit.quickcheck.When
import fk.Laws.NOT_NULL
import fk.Laws.assertEqual
import fk.identity

interface AltLaws : FunctorLaws {

    override fun <A : Any> of(): (a: A) -> Alt<A>

    @Property
    fun <A : Any> altAssociativity(
            @When(satisfies = NOT_NULL) a: A) {

        val of = of<A>()

        val x = of(a)
        val y = of(a)
        val z = of(a)

        x.alt(y).alt(z) assertEqual x.alt(y.alt(z))
    }

    @Property
    fun <A : Any> altDistributivity(
            @When(satisfies = NOT_NULL) a: A) {

        val of = of<A>()

        val x = of(a)
        val y = of(a)
        val f = identity<A>()

        x.alt(y).map(f) assertEqual x.map(f).alt(y.map(f))
    }

}
