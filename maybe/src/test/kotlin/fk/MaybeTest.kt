package fk

import com.pholser.junit.quickcheck.runner.JUnitQuickcheck
import fk.algebra.Monad
import fk.algebra.MonadLaws
import org.junit.runner.RunWith

@RunWith(JUnitQuickcheck::class)
class MaybeTest : MonadLaws {

    override fun <A : Any> of(): (a: A) -> Monad<A> = { a -> Maybe.of(a) }

}
