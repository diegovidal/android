package oo

/**
 * Created by diegovidal on 14/03/2018.
 */

class Source<out T>(val t: T) {

//    fun consumeT(t: T){ // YOU CAN'T
//
//    }

    fun produceT(): T{
        return t
    }
}

class Sink<in T> {

    fun consume(t: T){
        // ...
    }

//    fun produceT(): T{ // YOU CAN'T
//
//    }

}

fun main(args: Array<String>) {

    val strSource: Source<String> = Source("Producer")
    val anySource: Source<Any> = strSource // out --> covariance
//    anySource.consumeT(Any())
    anySource.produceT()

    val anySink: Sink<Any> = Sink()
    val strSink: Sink<String> = anySink //  in --> contravariance
    strSink.consume("Consumer")

    // Invariance = neither covariant nor contravariant <? extends E>
}
