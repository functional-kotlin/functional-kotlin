package fk.algebra

import com.pholser.junit.quickcheck.Property
import com.pholser.junit.quickcheck.When
import fk.Laws.NOT_NULL
import fk.Laws.assertEqual
import fk.identity

interface ComonadLaws {

    fun <A : Any> of(): (a: A) -> Comonad<A>

    @Property
    fun <A : Any> monadLeftIdentity(
            @When(satisfies = NOT_NULL) a: A)
            = of<A>()(a).extend(identity<Comonad<A>>()).extract() assertEqual of<A>()(a)

    @Property
    fun <A : Any> monadRightIdentity(
            @When(satisfies = NOT_NULL) a: A)
            = of<A>()(a).extend { w: Comonad<A> -> w.extract() } assertEqual of<A>()(a)

}
