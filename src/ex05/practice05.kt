package ex05
import ex05.FunList.*

fun main() {

    val list: FunList<Int> = Cons(1, Cons(2, Nil))
    val list2: FunList<Int> = Cons(1, Cons(2, Cons(3, Cons(4, Cons(5, Nil)))))
    val list3: FunList<Double> = Cons(1.0,Cons(2.0,Cons(3.0,Cons(4.0,Cons(5.0,Nil)))))

    list2.appendTail(6);
    println(list3.getHead())
    println(list.getHead())
    println(list2.drop(2))

}

sealed class FunList<out T>{
    object Nil : FunList<Nothing>()
    data class Cons<out T> (val head: T, val tail: FunList<T>) : FunList<T>()
}

//addHead
fun <T> FunList<T>.addHead(head: T) : FunList<T> = Cons(head, this)

//fun <T> FunList<T>.appendTail(value: T) : FunList<T> {
//    return when(this){
//        Nil -> Cons(value, Nil)
//        is Cons -> Cons(head, tail.appendTail(value))
//    }
//}

tailrec fun <T> FunList<T>.appendTail(value: T, acc: FunList<T> = Nil) : FunList<T> = when(this){
    Nil -> Cons(value, acc).reverse()
    is Cons -> tail.appendTail(value, acc.addHead(head))
}

tailrec fun <T> FunList<T>.reverse(acc: FunList<T> = FunList.Nil): FunList<T> = when(this){
    Nil -> acc
    is Cons -> tail.reverse(acc.addHead(head))
}


//5-3 리스트에 첫번째 값 가져오는 getHead
fun <T> FunList<T>.getHead(): T = when(this){
    Nil -> throw NoSuchElementException()
    is Cons -> head
}

fun <T> FunList<T>.getTail(): FunList<T> = when(this){
    Nil -> throw NoSuchElementException()
    is Cons -> tail
}


//5-4 리스트에 앞의 값 n개 제외된 리스트를 반환하는 drop 함수 구현
tailrec fun <T> FunList<T>.drop(n: Int): FunList<T> = when{
    n < 0 -> throw IllegalArgumentException()
    n == 0 -> this
    else -> getTail().drop(n - 1)
}
//5-5 타입 T를 입력받아 Boolean을 반환하는 함수 p를 입력받는다. 함수 p를 만족하기 전까진 drop한다.
tailrec fun <T> FunList<T>.dropWhile(p: (T) -> Boolean): FunList<T> = when(this){
    Nil -> this
    is Cons -> if( p(head)){
        this
    } else {
        tail.dropWhile(p)
    }
}

//5-6 리스트에 앞의 값 n개 값을 가진 리스트를 반환하는 take 함수 구현
tailrec fun <T> FunList<T>.take(n: Int, acc: FunList<T> = Nil): FunList<T> = when{
    n < 0 -> throw IllegalArgumentException()
    n == 0 -> acc.reverse()
    else -> getTail().take(n-1, acc.addHead(getHead()))
}


