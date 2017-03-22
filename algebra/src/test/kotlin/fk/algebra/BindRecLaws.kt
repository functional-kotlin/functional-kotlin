package fk.algebra

import com.pholser.junit.quickcheck.Property
import com.pholser.junit.quickcheck.When
import fk.Properties.NOT_NULL

interface BindRecLaws {

    @Property
    fun <A : Any> equivalence(
            @When(satisfies = NOT_NULL) a: A) {

//        val x = T [chainRec]((next, done, v) => p(v) ? d(v)[map](done) : n(v)[map](next), x);
//        val y = (function step (v) { return p(v) ? d(v) : n(v)[chain](step); }(x));
//
//        assert(
//                x == y
//        )
    }

}
