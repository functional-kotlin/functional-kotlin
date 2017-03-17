package fk.algebra

import com.pholser.junit.quickcheck.Property
import com.pholser.junit.quickcheck.When
import fk.Properties.NOT_NULL
import fk.andThen
import fk.identity

interface FunctorLaws {

    fun <A : Any> of(): (a: A) -> Functor<A>

    @Property
    fun <A : Any> functorIdentity(
            @When(satisfies = NOT_NULL) a: A) {

        val of = of<A>()

        assert(
                of(a).map<A>(identity()) == of(a)
        )
    }

    fun <A : Any, B : Any, C : Any> functorComposition(
            @When(satisfies = NOT_NULL) a: A,
            @When(satisfies = NOT_NULL) b: B,
            @When(satisfies = NOT_NULL) c: C) {

        val of = of<A>()
        val f = { a: A -> b }
        val g = { b: B -> c }

        assert(
                of(a).map(f).map(g) == of(a).map(f andThen g)
        )
    }

}
