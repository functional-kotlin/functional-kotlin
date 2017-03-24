package fk.algebra

import com.pholser.junit.quickcheck.Property
import com.pholser.junit.quickcheck.When
import fk.Laws.NOT_NULL
import fk.Laws.assertEqual

interface AlternativeLaws : PlusLaws, ApplicativeLaws {

    override fun <A : Any> of(): (a: A) -> Alternative<A>

    override fun <A : Any> zero(): () -> Alternative<A>

    @Property
    fun <A : Any, B : Any> alternativeRightAbsorption(
            @When(satisfies = NOT_NULL) b: B) {

        val ff = of<(A) -> B>()({ b })

//        assert(
//                (ff apply zero<A>()) == zero<B>()
//        )
    }

    @Property
    fun <A : Any, B : Any> alternativeDistributivity(
            @When(satisfies = NOT_NULL) a: A,
            @When(satisfies = NOT_NULL) b: B) {

        val ofA = of<A>()
        val ofAB = of<(A) -> B>()
        val x = ofA(a)
        val f = ofAB({ b })
        val g = ofAB({ b })

        x apply (f.alt(g)) assertEqual x.apply(f).alt(x.apply(g))
    }

    @Property
    fun <A : Any> alternativeAnnihilation(
            @When(satisfies = NOT_NULL) a: A) {

        val of = of<A>()
        val zero = zero<(A) -> A>()

        of(a).apply(zero()) assertEqual zero()
    }

}
