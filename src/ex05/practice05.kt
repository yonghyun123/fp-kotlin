package ex05
import ex05.FunList.*


fun main() {


    val list: FunList<Int> = Cons(1, Cons(2, Nil))
    //5-1
    val list2: FunList<Int> = Cons(1, Cons(2, Cons(3, Cons(4, Cons(5, Nil)))))
    //5-2
    val list3: FunList<Double> = Cons(1.0,Cons(2.0,Cons(3.0,Cons(4.0,Cons(5.0,Nil)))))


    list2.appendTail(6);
    println(list3.getHead())
    println(list.getHead())
    println(list2.drop(2))
    println(sumByFoldLeft2(list2))
    println(list2.maximumByFoldLeft())
    println(list2.filterByFoldLeft { it > 2 })
    println(list2.reverseByFoldRight())
    println(list2.filterByFoldRight { it > 2 })

    val bigIntList = (10000000 downTo 1).toList()
    val start = System.currentTimeMillis()

    realFunctionalWay(bigIntList)
    println("${System.currentTimeMillis() - start} ms")

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

tailrec fun <T, R> FunList<T>.map(acc: FunList<R> = Nil, f: (T) -> R): FunList<R> = when (this) {
    Nil -> acc.reverse()
    is Cons -> tail.map(acc.addHead(f(head)), f)
}
tailrec fun <T, R> FunList<T>.foldLeft(acc: R, f: (R,T) -> R): R = when (this) {
    Nil -> acc
    is Cons -> tail.foldLeft( f(acc, head), f)
}
tailrec fun <T, R> FunList<T>.foldRight(acc: R, f: (T,R) -> R): R = when (this) {
    Nil -> acc
    is Cons -> f(head, tail.foldRight(acc,f))
}

fun sumByFoldLeft(list: FunList<Int>): Int = list.foldLeft(0){
    acc, x -> acc + x
}

fun sumByFoldLeft2(list: FunList<Int>): Int {
    return list.foldLeft(0) { acc, x ->
        acc + x
    }
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
//5-8
tailrec fun <T, R> FunList<T>.indexedMap(index : Int = 0, acc: FunList<R> = Nil, f: (Int, T) -> R): FunList<R> = when (this) {
    Nil -> acc.reverse()
    is Cons -> tail.indexedMap(index+1 ,acc.addHead(f(index, head)), f)
}

//5-9
fun FunList<Int>.maximumByFoldLeft(): Int = this.foldLeft(0) { acc, v1 ->
    if (acc > v1) acc
    else v1
}

//5-10 풀었는데 타입을 잘못써서....
fun <T> FunList<T>.filterByFoldLeft(p: (T) -> Boolean): FunList<T> = this.foldLeft(Nil) { acc: FunList<T>, x ->
    if(p(x))  acc.appendTail(x)
    else acc
}

//5-11
fun <T> FunList<T>.reverseByFoldRight(): FunList<T> = foldRight(Nil) { x, acc: FunList<T> ->
    acc.appendTail(x)
}

//5-12
fun <T> FunList<T>.filterByFoldRight(p: (T) -> Boolean): FunList<T> = foldRight(Nil) { x, acc: FunList<T> ->
    if(p(x))  acc.addHead(x) //낚시 조심
    else acc
}

//5-16
fun realFunctionalWay(intList: List<Int>) : Int =
    intList.asSequence()
        .map{v -> v * v}
        .filter{v -> v < 10}
        .first()

