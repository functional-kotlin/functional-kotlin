package fk

object Laws {

    internal const val NOT_NULL = "#_!=null"

    infix fun <A : Any> A.assertEqual(other: A)
            = assert(this == other)

}
