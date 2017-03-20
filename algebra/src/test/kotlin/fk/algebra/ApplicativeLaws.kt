package fk.algebra

import com.pholser.junit.quickcheck.Property
import com.pholser.junit.quickcheck.When
import fk.Properties.NOT_NULL
import fk.identity
import fk.thrush

interface ApplicativeLaws : ApplyLaws {

    override fun <A : Any> of(): (a: A) -> Applicative<A>

    @Property fun <A : Any> applyIdentity(
            @When(satisfies = NOT_NULL) a: A) {

        val ofA = of<A>()
        val ofAA = of<(A) -> A>()
        val x = ofA(a).ap(ofAA(identity()))
        val y = ofA(a)

        assert(
                x == y
        )
    }

    @Property fun <A : Any> applyHomomorphism(
            @When(satisfies = NOT_NULL) a: A) {

        val ofA = of<A>()
        val ofAA = of<(A) -> A>()
        val x = ofA(a).ap(ofAA(identity()))
        val y = ofAA({ a })

        assert(
                x == y
        )
    }

//    @Property fun <A : Any> applyInterchange(
//            @When(satisfies = NOT_NULL) a: A) {
//
//        val ofA = of<A>()
//        val ofAA = of<(A) -> A>()
//        val u = ofAA(identity())
//        val x = ofA(a).ap(u)
//        val y = u.ap(ofAA(thrush()(a)))
//
//        assert(
//                x == y
//        )
//    }

}
