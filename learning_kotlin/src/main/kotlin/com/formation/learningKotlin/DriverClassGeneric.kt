package com.formation.learningKotlin

class Box<T>(var variable: T){
    fun get(): T {
        return variable;
    }

    fun set(v: T){
        this.variable = v
    }
}

fun main() {
    val box = Box<Int>(5)
    val v: Int = box.get()
    println("Ma variable : $v")

    val language = Box<String>("Kotlin")
    language.set("Golang")
    println("My language : ${language.get()}")



}