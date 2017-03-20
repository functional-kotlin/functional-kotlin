package fk.algebra

import com.pholser.junit.quickcheck.Property
import com.pholser.junit.quickcheck.When
import fk.Properties.NOT_NULL
import fk.compose
import fk.identity

interface BifunctorLaws {

    fun <A : Any, B : Any> of(): (a: A) -> Bifunctor<A, B>

    @Property
    fun <A : Any, B : Any> bifunctorIdentity(
            @When(satisfies = NOT_NULL) a: A) {

        val of = of<A, B>()

        val x = of(a).bimap(identity<A>(), identity<B>())
        val y = of(a)

        assert(
                x == y
        )
    }

    @Property
    fun <A : Any, B : Any> bifunctorComposition(
            @When(satisfies = NOT_NULL) a: A) {

        val of = of<A, B>()

        val x = of(a).bimap(identity<A>() compose identity<A>(), identity<B>() compose identity<B>())
        val y = of(a).bimap(identity<A>(), identity<B>()).bimap(identity<A>(), identity<B>())

        assert(
                x == y
        )
    }

}
