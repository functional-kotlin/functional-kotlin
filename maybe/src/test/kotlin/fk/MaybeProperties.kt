package fk

import com.pholser.junit.quickcheck.Property
import com.pholser.junit.quickcheck.When
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck
import fk.algebra.ApplyLaws
import fk.algebra.BindLaws
import fk.algebra.FunctorLaws
import org.junit.runner.RunWith

@RunWith(JUnitQuickcheck::class)
class MaybeProperties {

    // Functor

    @Property
    fun <A : Any> functorIdentity(
            @When(satisfies = NOT_NULL) a: A) {

        assert(FunctorLaws.functorIdentity(of(a)))
    }

    @Property
    fun <A : Any, B : Any, C : Any> functorComposition(
            @When(satisfies = NOT_NULL) a: A,
            @When(satisfies = NOT_NULL) b: B,
            @When(satisfies = NOT_NULL) c: C) {

        assert(FunctorLaws.functorComposition(of(a), { b }, { c }))
    }

    // Apply

    @Property
    fun <A : Any, B : Any, C : Any> applyComposition(
            @When(satisfies = NOT_NULL) a: A,
            @When(satisfies = NOT_NULL) b: B,
            @When(satisfies = NOT_NULL) c: C) {

        assert(ApplyLaws.applyComposition(of(a), of({ _: A -> b }), of({ _: B -> c })))
    }

    // Bind

    @Property
    fun <A : Any, B : Any, C : Any> bindAssociativity(
            @When(satisfies = NOT_NULL) a: A,
            @When(satisfies = NOT_NULL) b: B,
            @When(satisfies = NOT_NULL) c: C) {

        assert(BindLaws.bindAssociativity(of(a), { of(b) }, { of(c) }))
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    companion object {

        private const val NOT_NULL = "#_!=null"

        private fun <A : Any> of(a: A) = Maybe.of(a)

    }

}
