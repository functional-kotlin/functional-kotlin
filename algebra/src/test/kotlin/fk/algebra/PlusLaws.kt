package fk.algebra

import com.pholser.junit.quickcheck.Property
import com.pholser.junit.quickcheck.When
import fk.Properties.NOT_NULL

interface PlusLaws : AltLaws {

    fun <A : Any> zero(): () -> Plus<A>

    override fun <A : Any> of(): (a: A) -> Plus<A>

    @Property
    fun <A : Any> plusRightIdentity(
            @When(satisfies = NOT_NULL) a: A) {

        val of = of<A>()
        val zero = zero<A>()
        val x = of(a)

        assert(
                zero().alt(x) == x
        )
    }

    @Property
    fun <A : Any> plusLeftIdentity(
            @When(satisfies = NOT_NULL) a: A) {

        val of = of<A>()
        val zero = zero<A>()
        val x = of(a)

        assert(
                x.alt(zero()) == x
        )
    }

    @Property
    fun <A : Any> plusAnnihilation(
            @When(satisfies = NOT_NULL) a: A) {

        val zero = zero<A>()
        val f = { a: A -> a }

        assert(
                zero().map(f) == zero()
        )
    }

}
