fun main(args: Array<String>) {
    val fpCalculator = FpCalculator();
    println(fpCalculator.calculate({n1, n2 -> n1 + n2}, 3, 1));
    println(lazeValue)
    infiniteValue.take(100).forEach { print("$it ") }
}

class FpCalculator{
    fun calculate(calculator: (Int, Int) -> Int,
                  num1: Int, num2: Int): Int = calculator(num1,num2)
}

val lazeValue: String by lazy{
    println("too late operation")
    "hello"
}

val infiniteValue = generateSequence(0) { it + 5 }



