package fk.algebra

import com.pholser.junit.quickcheck.Property
import com.pholser.junit.quickcheck.When
import fk.Properties.NOT_NULL

interface MonadLaws : BindLaws {

    override fun <A : Any> of(): (a: A) -> Monad<A>

    @Property
    fun <A : Any> monadLeftIdentity(
            @When(satisfies = NOT_NULL) a: A) {

        val of = of<A>()

        assert(
                of(a).bind(of) == of(a)
        )
    }

    @Property
    fun <A : Any> monadRightIdentity(
            @When(satisfies = NOT_NULL) a: A) {

        val of = of<A>()

        assert(
                of(a).bind(of) == of(a)
        )
    }

}
