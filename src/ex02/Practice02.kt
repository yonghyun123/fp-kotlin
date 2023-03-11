package ex02

fun main() {
    "Hello, ".product("kotlin");
//    require("kotlin".addHelloPrefix() == "Hello, kotlin")
//    require("FP".addHelloPrefix() == "Hello, FP")
    print("$result")
}


fun String.product(value: String) {
    println(this + value);
}

/**
 * 답안지
 * 불친절하네..
 */

private fun String.addHelloPrefix(): String = TODO();


/**
 * let을 이용한 객체 변경
 */

val person = Person("FP", 30)
val result = person.let{
    it.name = "Kotlin"
    it.age = 10
    it
}

data class Person(var name: String, var age: Int){

}