package fk.algebra

import com.pholser.junit.quickcheck.Property
import com.pholser.junit.quickcheck.When
import fk.Properties.NOT_NULL


interface SemigroupLaws {

    fun <A : Any> of(): (a: A) -> Semigroup<A>

    @Property
    fun <A : Any> semigroupAssociativity(
            @When(satisfies = NOT_NULL) x: A,
            @When(satisfies = NOT_NULL) y: A,
            @When(satisfies = NOT_NULL) z: A) {

        val of = of<A>()

        assert(
                of(x).concat(of(y)).concat(of(z)) == of(x).concat(of(y).concat(of(z)))
        )
    }

}
