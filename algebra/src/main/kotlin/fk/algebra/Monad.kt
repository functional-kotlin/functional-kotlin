package fk.algebra

interface Monad<A : Any> : Applicative<A>, Bind<A> {

    // Overloads

    infix fun <B : Any> apply(monad: Monad<(A) -> B>): Monad<B>

    infix fun <B : Any> bind(f: (A) -> Monad<B>): Monad<B>

    // Overrides

    override fun <B : Any> map(f: (A) -> B): Monad<B>

    override fun <B : Any> apply(apply: Apply<(A) -> B>): Apply<B>
            = apply(apply as Monad<(A) -> B>)

    override fun <B : Any> apply(applicative: Applicative<(A) -> B>): Applicative<B>
            = apply(applicative as Monad<(A) -> B>)

    override fun <B : Any> apply(bind: Bind<(A) -> B>): Bind<B>
            = apply(bind as Monad<(A) -> B>)

    override fun <B : Any> bind(f: (A) -> Bind<B>): Bind<B>
            = bind(f as (A) -> Monad<B>)

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    interface Companion : Applicative.Companion {

        override fun <A : Any> of(a: A): Monad<A>

    }

}
