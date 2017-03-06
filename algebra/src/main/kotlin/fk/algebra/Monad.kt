package fk.algebra

interface Monad<A : Any> : Applicative<A>, Bind<A>
