package fk.algebra

import java.lang.reflect.Field
import java.lang.reflect.Method

interface Monoid<A : Any> : Semigroup<A> {

    // Overloads

    infix fun concat(other: Monoid<A>): Monoid<A>

    infix operator fun plus(other: Monoid<A>): Monoid<A>
            = concat(other)

    // Overrides

    override fun concat(other: Semigroup<A>): Semigroup<A>
            = concat(other as Monoid<A>)

    override fun plus(other: Semigroup<A>): Semigroup<A>
            = plus(other as Monoid<A>)

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////


    companion object {

        val FIELD_CACHE: MutableMap<String, Field> = HashMap()
        val METHOD_CACHE: MutableMap<String, Method> = HashMap()

        inline fun <reified M : Monoid<*>> zero(): M {
            val cls = M::class.java
            val fqcn = cls.name
            val field = FIELD_CACHE.getOrPut(fqcn) { cls.getDeclaredField("Companion") }
            val method = METHOD_CACHE.getOrPut(fqcn) { field.get(null).javaClass.getDeclaredMethod("zero") }
            return method.apply { isAccessible = true }.invoke(field) as M
        }

    }

}
