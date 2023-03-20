package ex04

//고차함수를 이용한 계산기
fun main() {
    val sum: (Int, Int) -> Int = {
        v1, v2 -> v1 + v2
    }

    val minus: (Int, Int) -> Int = {
        v1, v2 -> v1 - v2
    }

    val divided: (Int, Int) -> Int = {
            v1, v2 -> v1 / v2
    }

    val multiple: (Int, Int) -> Int = {
            v1, v2 -> v1 * v2
    }

    println(higherOrder(sum, 2, 2));
    println(higherOrder(minus, 2, 2));
    println(higherOrder(divided, 2, 2));
    println(higherOrder(multiple, 2, 2));
}

private fun higherOrder(func: (Int, Int) -> Int, v1:Int, v2:Int) : Int{
    return func(v1,v2)
}

private fun sum (v1: Int, v2: Int) : Int{
    return v1 + v2;
}

private fun minus (v1: Int, v2: Int) : Int{
    return v1 - v2;
}

private fun divied (v1: Int, v2: Int) : Int{
    return v1 / v2;
}

private fun multiple (v1: Int, v2: Int) : Int{
    return v1 * v2;
}