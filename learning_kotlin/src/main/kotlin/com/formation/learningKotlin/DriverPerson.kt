package com.formation.learningKotlin

data class User(val name: String, val age: Int){
    lateinit var nickname: String



    override fun toString(): String {
        return "name: $name - age : $age"
    }
}

fun main() {

    val marko = User("Marko", 47)

    marko.nickname = "Simeon"
    println("Je suis ${marko.name} et mon surnom est ${marko.nickname}\n")


    val john = User("John", 45)
    println("Infos User : $john")

    val ronaldo = User("Ronaldo", 42)
    println("Infos User : $ronaldo")

    val jp = john.copy("jp")
    println("Infos User : $jp")

    if (john == jp){
        println("Ils sont égaux")
    }else{
        println("ils sont différents")
    }

}