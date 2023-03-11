package ex03

fun main() {
    println(fiboDynamic(10, IntArray(100)) )
    println(power(3.0, 3))
    println(factorial(5))
    println(toBinary(7))
    println(replicate(5,5))
    println(elem(3, listOf(1,2,5)))

}

private fun fiboDynamic(n: Int, fibo: IntArray): Int {
    fibo[0] = 0;
    fibo[1] = 1;

    for (i in 2..n) {
        fibo[i] = fibo[i - 1] + fibo[i - 2]
    }
    return fibo[n];
}

//3-2 X의 n승을 구하는 함수를 재귀로 구해보자
private fun power(x: Double, n: Int): Double = when(n){
    1 -> x
    else -> x * power(x, n - 1)

}

//3-3 Factorial을 재귀로 구현하자
private fun factorial(n: Int): Int = when(n){
    1 -> n
    else -> n * factorial(n - 1)
}

//3-4 10진수 입력받아서 2준수로 변환하라
private fun toBinary(n: Int): String = when (n) {
    0 -> ""
    else -> {
        val last = n % 2
        toBinary(n/2) + last
    }
}
//3-5 두 수를 입력받아 두번째 수를 첫번째숫자만큼 가지고 있는 리스트를 반환하라
// ex) replicate(3,5) -> [5,5,5]

private fun replicate(n: Int, element: Int) : List<Int> = when{
    n == 0 -> listOf();
    else ->{
        listOf(element) + replicate(n-1, element)
    }

}

//3-6 입력값 n이 리스트에 존재하는지 화인하는 함수를 재귀로 작성하세요.
private fun elem(num: Int, list: List<Int>) : Boolean = when{
    list.isEmpty() -> false
    num == list.first() -> true
    else -> {
        elem(num, list.drop(1))
    }
}

fun String.head() = first()
fun String.tail() = drop(1)
