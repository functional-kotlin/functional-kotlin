package fk.algebra

object BindLaws {

    fun <A : Any, B : Any, C : Any> bindAssociativity(fa: Bind<A>, f: (A) -> Bind<B>, g: (B) -> Bind<C>)
            = fa.bind(f).bind(g) == fa.bind { a -> f(a).bind(g) }

}
