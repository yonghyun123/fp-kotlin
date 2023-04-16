package ex05

fun main() {

    val listStream = FunStream.Cons({ 1 }, { FunStream.Cons({ 2 }, { FunStream.Cons({3}, {FunStream.Nil}) }) })
    println("${listStream.getHead()} 와 ${listStream.getTail()}")



}

sealed class FunStream<out T>{
    object Nil : FunStream<Nothing>()
    data class Cons<out T>(val head: () -> T, val tail: () -> FunStream<T>) : FunStream<T>()
}

fun <T> FunStream<T>.getHead(): T = when(this){
    FunStream.Nil -> throw NoSuchElementException()
    is FunStream.Cons -> head()
}

fun <T> FunStream<T>.getTail(): FunStream<T> = when(this){
    FunStream.Nil -> throw NoSuchElementException()
    is FunStream.Cons -> tail()
}

//5-17
//fun FunStream<Int>.sum(): Int = {
//    when(this){
//        this
//    }
//}
