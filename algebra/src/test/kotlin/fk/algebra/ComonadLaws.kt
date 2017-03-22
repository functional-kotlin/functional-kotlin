package fk.algebra

import com.pholser.junit.quickcheck.Property
import com.pholser.junit.quickcheck.When
import fk.Properties.NOT_NULL
import fk.identity

interface ComonadLaws {

    fun <A : Any> of(): (a: A) -> Comonad<A>

    @Property
    fun <A : Any> monadLeftIdentity(
            @When(satisfies = NOT_NULL) a: A) {

        val of = of<A>()

        assert(
                of(a).extend(identity<Comonad<A>>()).extract() == of(a)
        )
    }

    @Property
    fun <A : Any> monadRightIdentity(
            @When(satisfies = NOT_NULL) a: A) {

        val of = of<A>()

        assert(
                of(a).extend { w: Comonad<A> -> w.extract() } == of(a)
        )
    }

}
