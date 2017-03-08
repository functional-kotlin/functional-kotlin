package fk.algebra

import fk.andThen
import fk.identity

object FunctorLaws {

    fun <A : Any> functorIdentity(fa: Functor<A>)
            = fa.map<A>(identity()) == fa

    fun <A : Any, B : Any, C : Any> functorComposition(fa: Functor<A>, f: (A) -> B, g: (B) -> C)
            = fa.map(f).map(g) == fa.map(f andThen g)

}
