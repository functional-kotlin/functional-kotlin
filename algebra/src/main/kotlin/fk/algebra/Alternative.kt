package fk.algebra

interface Alternative<A : Any> : Plus<A>, Applicative<A> {

    override infix fun <B : Any> map(f: (A) -> B): Alternative<B>

}
