package com.formation.learningKotlin

fun main() {
    val integers = arrayOf(1, 2, 3, 4)
    val strings = arrayOf("hello", "world", "en", "kotlin")

    printArray(integers)
    printArray(strings)

}

fun <T> printArray(array: Array<T>) {

    var separator = ""
    val sb = StringBuilder()
    for (i in array.indices.reversed()) {
        sb.append(separator)
        sb.append(array[i])
        separator = ", "
    }

    println(sb.toString())

}