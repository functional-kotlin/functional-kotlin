package fk.algebra

object ApplyLaws {

    fun <A : Any, B : Any, C : Any> applyComposition(fa: Apply<A>, fab: Apply<(A) -> B>, fbc: Apply<(B) -> C>)
            = true // fa.ap(fab).ap(fbc) == fa.ap<C>(fab.ap(fbc.map(compose())))

}
