package fk.algebra

import com.pholser.junit.quickcheck.Property
import com.pholser.junit.quickcheck.When
import fk.Laws.NOT_NULL
import fk.Laws.assertEqual

interface BindLaws : ApplyLaws {

    override fun <A : Any> of(): (a: A) -> Bind<A>

    @Property
    fun <A : Any, B : Any, C : Any> bindAssociativity(
            @When(satisfies = NOT_NULL) a: A,
            @When(satisfies = NOT_NULL) b: B,
            @When(satisfies = NOT_NULL) c: C) {

        val f = { a: A -> of<B>()(b) }
        val g = { b: B -> of<C>()(c) }

        of<A>()(a).bind(f).bind(g) assertEqual of<A>()(a).bind { a -> f(a).bind(g) }
    }

}
