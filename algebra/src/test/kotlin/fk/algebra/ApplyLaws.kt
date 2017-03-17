package fk.algebra

import com.pholser.junit.quickcheck.Property
import com.pholser.junit.quickcheck.When
import fk.Properties.NOT_NULL

interface ApplyLaws : FunctorLaws {

    override fun <A : Any> of(): (a: A) -> Apply<A>

    @Property
    fun <A : Any, B : Any, C : Any> applyComposition(
            @When(satisfies = NOT_NULL) a: A,
            @When(satisfies = NOT_NULL) b: B,
            @When(satisfies = NOT_NULL) c: C) {

        val of = of<A>()
        val f = { a: A -> of<B>()(b) }
        val g = { b: B -> of<C>()(c) }

//        assert(
//                of(a).ap(f).ap(g) == of(a).ap(f.ap(g.map(compose())))
//        )
    }

}
