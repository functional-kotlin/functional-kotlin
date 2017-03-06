package fk.algebra

interface Functor<A : Any> {

    /**
     * Transforms a Functor<A> to a Functor<B>
     *
     * @param f A function which takes an A and returns a B
     */
    infix fun <B : Any> map(f: (A) -> B): Functor<B>

}
