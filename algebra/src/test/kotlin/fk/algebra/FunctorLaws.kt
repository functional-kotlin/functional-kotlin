package fk.algebra

import com.pholser.junit.quickcheck.Property
import com.pholser.junit.quickcheck.When
import fk.Laws.NOT_NULL
import fk.Laws.assertEqual
import fk.andThen
import fk.identity

interface FunctorLaws {

    fun <A : Any> of(): (a: A) -> Functor<A>

    @Property
    fun <A : Any> functorIdentity(
            @When(satisfies = NOT_NULL) a: A)
            = of<A>()(a).map<A>(identity()) assertEqual of<A>()(a)

    fun <A : Any, B : Any, C : Any> functorComposition(
            @When(satisfies = NOT_NULL) a: A,
            @When(satisfies = NOT_NULL) b: B,
            @When(satisfies = NOT_NULL) c: C) {

        val of = of<A>()
        val f = { a: A -> b }
        val g = { b: B -> c }

        of(a).map(f).map(g) assertEqual of(a).map(f andThen g)
    }

}
