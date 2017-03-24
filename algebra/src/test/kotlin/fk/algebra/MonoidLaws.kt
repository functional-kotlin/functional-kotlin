package fk.algebra

import com.pholser.junit.quickcheck.Property
import com.pholser.junit.quickcheck.When
import fk.Laws.NOT_NULL
import fk.Laws.assertEqual

interface MonoidLaws : SemigroupLaws {

    fun <A : Any> zero(): () -> Monoid<A>

    override fun <A : Any> of(): (a: A) -> Monoid<A>

    @Property
    fun <M : Monoid<A>, A : Any> monoidRightIdentity(
            @When(satisfies = NOT_NULL) m: M)
            = m assertEqual m.concat(zero<A>()())

    @Property
    fun <A : Any> monoidLeftIdentity(
            @When(satisfies = NOT_NULL) a: A)
            = of<A>()(a) assertEqual zero<A>()().concat(of<A>()(a))

}
