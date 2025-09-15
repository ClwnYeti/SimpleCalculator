package itmo.isit.clwnyeti.simplecalculator.logic

enum class Operations(val text: String) {
    Plus("+"),
    Minus("-"),
    Divide("/"),
    Multiply("*");

    fun toText(): String = text
}