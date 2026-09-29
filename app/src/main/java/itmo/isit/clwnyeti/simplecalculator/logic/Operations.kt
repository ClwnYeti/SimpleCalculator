package itmo.isit.clwnyeti.simplecalculator.logic

interface Operations
enum class OneNumberOperations(val text: String) : Operations {
    Equals("="),
    Clear("C"),
    Point(",");

    fun toText(): String = text
}

enum class TwoNumbersOperations(val text: String) : Operations {
    Plus("+"),
    Minus("-"),
    Divide("/"),
    Multiply("*");

    fun toText(): String = text
}