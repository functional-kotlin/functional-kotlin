package fk.algebra

import com.pholser.junit.quickcheck.Property
import com.pholser.junit.quickcheck.When
import fk.Properties.NOT_NULL

interface AlternativeLaws : PlusLaws, ApplicativeLaws {

    override fun <A : Any> of(): (a: A) -> Alternative<A>

    override fun <A : Any> zero(): () -> Alternative<A>

    @Property
    fun <A : Any> alternativeDistributivity(
            @When(satisfies = NOT_NULL) a: A) {

        val of = of<A>()
        val x = of(a)
        val f = of(a)

//        assert(
//                x.ap(f.alt(g)) == x.ap(f).alt(x.ap(g))
//        )
    }

    @Property
    fun <A : Any> alternativeAnnihilation(
            @When(satisfies = NOT_NULL) a: A) {

        val of = of<A>()
        val zero = zero<(A) -> A>()

        assert(
                of(a).ap(zero()) == zero()
        )
    }

}
