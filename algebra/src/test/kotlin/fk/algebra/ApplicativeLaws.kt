package fk.algebra

import com.pholser.junit.quickcheck.Property
import com.pholser.junit.quickcheck.When
import fk.Laws.NOT_NULL
import fk.Laws.assertEqual
import fk.identity

interface ApplicativeLaws : ApplyLaws {

    override fun <A : Any> of(): (a: A) -> Applicative<A>

    @Property fun <A : Any> applyIdentity(
            @When(satisfies = NOT_NULL) a: A) {

        val ofA = of<A>()
        val ofAA = of<(A) -> A>()
        val x = ofA(a).apply(ofAA(identity()))
        val y = ofA(a)

        x assertEqual y
    }

    @Property fun <A : Any> applyHomomorphism(
            @When(satisfies = NOT_NULL) a: A) {

        val ofA = of<A>()
        val ofAA = of<(A) -> A>()
        val x = ofA(a).apply(ofAA(identity()))
        val y = ofAA({ a })

        x assertEqual y
    }

//    @Property fun <A : Any> applyInterchange(
//            @When(satisfies = NOT_NULL) a: A) {
//
//        val ofA = of<A>()
//        val ofAA = of<(A) -> A>()
//        val u = ofAA(identity())
//        val x = ofA(a).apply(u)
//        val y = u.apply(ofAA(thrush()(a)))
//
//        assert(
//                x == y
//        )
//    }

}
