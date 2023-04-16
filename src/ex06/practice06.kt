package ex06
import ex06.BTree.Node
import java.util.NoSuchElementException

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

    val node = BTree.Node(1,BTree.EmptyTree,BTree.EmptyTree)


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

//6-1 이진트리 만들기

sealed class BTree<out T>{
    data class Node<out T>(
        val value: T,
        val leftTree: BTree<T>,
        val rightTree: BTree<T>
    ) : BTree<T>()
    object EmptyTree : BTree<Nothing>() //문법이 안통해
}




//6-2 이진트리 인서트
tailrec fun BTree<Int>.insert(elem: Int): Node<Int> =
    when (this) {
        is Node -> when {
            elem <= value -> Node(value, leftTree.insert(elem), rightTree)
            else -> Node(value, leftTree, rightTree.insert(elem))
        }
//        else -> Node(elem, BTree.EmptyTree, BTree.EmptyTree)
        else -> throw NoSuchElementException()
    }

//6-3