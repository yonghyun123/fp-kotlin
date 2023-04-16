package ex06

import java.util.Objects

fun main() {

    val maybe1:Maybe<Int> = Just<Int>(5)
    val maybe2 = Just(3)

    println("value = ${maybe1} value2=${maybe2}")

    val frontend = object : Frontend {}
    val backend = object : Backend {}

    frontend.writeCode()
    backend.writeCode()

    val fullStack = FullStack()

    fullStack.writeCode()


}

sealed class Maybe<T>
object Nothing: Maybe<kotlin.Nothing>()
data class Just<T>(val value: T): Maybe<T>()

interface Developer{
    val language: String

    fun writeCode(){
        println("write $language")
    }
}

interface Backend : Developer {
    fun operateEnvironment(): String{
        return "operateEnvironment"
    }

    override val language: String
        get() = "Haskell"
}

interface Frontend : Developer {
    fun drawUI(): String{
        return "drawUI"
    }

    override val language: String
        get() = "Elm"
}

class FullStack: Frontend, Backend{
    override val language: String
        get() = super<Backend>.language + super<Frontend>.language
}