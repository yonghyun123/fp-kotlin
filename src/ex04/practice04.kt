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

class PartialFunction<in P, out R>(
    private val condition: (P) -> Boolean,
    private val f: (P) -> R
) : (P) -> R{

    override fun invoke(p: P): R = when{
        condition(p) -> f(p)
        else -> throw java.lang.IllegalStateException("$p isn't supported")
    }

    //이거 맞지 않나
    fun invokeOrElse(p: P, default: @UnsafeVariance R): R = when{
        condition(p)  -> f(p)
        else -> default

//        isDefinedAt(p) ->  invoke(p)
//        else -> default
    }

    //이해못함...
    fun orElse(that: PartialFunction<@UnsafeVariance P, @UnsafeVariance R>): PartialFunction<P, R> =
        PartialFunction({ it: P -> this.isDefinedAt(it) || that.isDefinedAt(it) },
            { it: P ->
                when {
                    this.isDefinedAt(it) -> this(it)
                    that.isDefinedAt(it) -> that(it)
                    else -> throw IllegalArgumentException("$it isn't defined")
                }
            }
        )



    fun isDefinedAt(p: P): Boolean = condition(p)
}