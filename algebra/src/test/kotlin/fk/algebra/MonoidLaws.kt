package fk.algebra

import com.pholser.junit.quickcheck.Property
import com.pholser.junit.quickcheck.When
import fk.Properties.NOT_NULL

interface MonoidLaws : SemigroupLaws {

    fun <A : Any> zero(): () -> Monoid<A>

    override fun <A : Any> of(): (a: A) -> Monoid<A>

    @Property
    fun <M : Monoid<A>, A : Any> monoidRightIdentity(
            @When(satisfies = NOT_NULL) m: M) {

        val zero = zero<A>()

        assert(
                m == m.concat(zero())
        )
    }

    @Property
    fun <A : Any> monoidLeftIdentity(
            @When(satisfies = NOT_NULL) a: A) {

        val of = of<A>()
        val zero = zero<A>()

        val m = of(a)

        assert(
                m == zero().concat(m)
        )
    }

}
