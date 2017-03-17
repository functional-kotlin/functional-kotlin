package fk

import com.pholser.junit.quickcheck.runner.JUnitQuickcheck
import fk.algebra.Monad
import fk.algebra.MonadLaws
import fk.algebra.Monoid
import org.junit.runner.RunWith

@RunWith(JUnitQuickcheck::class)
class WriterLaws : MonadLaws {

    override fun <A : Any> of(): (a: A) -> Monad<A> = { a -> null as Monad<A> }

    class MonoidString(
            val value: String) : Monoid<String> {

        override fun concat(other: Monoid<String>): Monoid<String> {
            throw UnsupportedOperationException("not implemented")
        }
    }

}
