package fk

import com.pholser.junit.quickcheck.Property
import com.pholser.junit.quickcheck.When
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck
import fk.algebra.BindLaws
import fk.algebra.FunctorLaws
import org.junit.runner.RunWith

@RunWith(JUnitQuickcheck::class)
class MaybeProperties {

    // Functor

    @Property
    fun <X : Any> functorIdentity(
            @When(satisfies = NOT_NULL) x: X) {

        FunctorLaws.identity(of(), x)
    }

    @Property
    fun <X : Any, Y : Any, Z : Any> functorComposition(
            @When(satisfies = NOT_NULL) x: X,
            @When(satisfies = NOT_NULL) y: Y,
            @When(satisfies = NOT_NULL) z: Z) {

        FunctorLaws.composition(of(), x, { y }, { z })
    }

    // Apply

    @Property
    fun <X : Any, Y : Any, Z : Any> applyComposition(
            @When(satisfies = NOT_NULL) x: X,
            @When(satisfies = NOT_NULL) y: Y,
            @When(satisfies = NOT_NULL) z: Z) {

//        ApplyLaws.composition(of(), x, { y }, { z })
    }

    // Bind

    @Property
    fun <X : Any> bindAssociativity(
            @When(satisfies = NOT_NULL) x: X) {

        BindLaws.associativity(of(), x)
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    companion object {

        private const val NOT_NULL = "#_!=null"

        private fun <A : Any> of() = { a: A -> Maybe.of(a) }

    }

}
