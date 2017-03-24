package fk.algebra

import com.pholser.junit.quickcheck.Property
import com.pholser.junit.quickcheck.When
import fk.Laws.NOT_NULL
import fk.Laws.assertEqual

interface SemigroupLaws {

    fun <A : Any> of(): (a: A) -> Semigroup<A>

    @Property
    fun <A : Any> semigroupAssociativity(
            @When(satisfies = NOT_NULL) x: A,
            @When(satisfies = NOT_NULL) y: A,
            @When(satisfies = NOT_NULL) z: A)
            = of<A>()(x).concat(of<A>()(y)).concat(of<A>()(z)) assertEqual of<A>()(x).concat(of<A>()(y).concat(of<A>()(z)))

}
