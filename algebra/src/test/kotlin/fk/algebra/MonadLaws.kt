package fk.algebra

import com.pholser.junit.quickcheck.Property
import com.pholser.junit.quickcheck.When
import fk.Laws.NOT_NULL
import fk.Laws.assertEqual

interface MonadLaws : BindLaws {

    @Property
    fun <A : Any> monadLeftIdentity(
            @When(satisfies = NOT_NULL) a: A)
            = of<A>()(a).bind(of<A>()) assertEqual of<A>()(a)

    @Property
    fun <A : Any> monadRightIdentity(
            @When(satisfies = NOT_NULL) a: A)
            = of<A>()(a).bind(of<A>()) assertEqual of<A>()(a)

}
