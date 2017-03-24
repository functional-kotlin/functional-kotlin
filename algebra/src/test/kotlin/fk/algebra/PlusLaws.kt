package fk.algebra

import com.pholser.junit.quickcheck.Property
import com.pholser.junit.quickcheck.When
import fk.Laws.NOT_NULL
import fk.Laws.assertEqual

interface PlusLaws : AltLaws {

    fun <A : Any> zero(): () -> Plus<A>

    override fun <A : Any> of(): (a: A) -> Plus<A>

    @Property
    fun <A : Any> plusRightIdentity(
            @When(satisfies = NOT_NULL) a: A)
            = zero<A>()().alt(of<A>()(a)) assertEqual of<A>()(a)

    @Property
    fun <A : Any> plusLeftIdentity(
            @When(satisfies = NOT_NULL) a: A)
            = of<A>()(a).alt(zero<A>()()) assertEqual of<A>()(a)

    @Property
    fun <A : Any> plusAnnihilation(
            @When(satisfies = NOT_NULL) a: A)
            = zero<A>()().map { a } assertEqual zero<A>()()

}
