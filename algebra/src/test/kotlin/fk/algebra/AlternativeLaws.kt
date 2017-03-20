package fk.algebra

interface AlternativeLaws : PlusLaws, ApplicativeLaws {

    override fun <A : Any> of(): (a: A) -> Alternative<A>

}
