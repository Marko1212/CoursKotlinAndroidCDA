package com.formation.learningKotlin

abstract class Vehicle(val wheelsCount: Int) {
    fun showWheels(){
        println("Nombre de roues : $wheelsCount")
    }
    abstract fun honk()
}

class Car() : Vehicle(4), Fuel {
    override var fuelGauge: Float = 0f

    override fun honk(){
        println("Pouet!")
    }
    fun honkForWheels(){
        println("Honking for wheels")
        for(i in 1..wheelsCount){
            honk()
        }
    }
}

class Motorcycle : Vehicle(2), Trick, Fuel {
    override var fuelGauge: Float = 10f

    override fun honk() {
        println("Tsouin!")
    }

    override fun wheeling() {
        println("Roue arrière en moto!")
    }
}

class Bicycle : Vehicle(2), Trick {
    override fun honk() {
        println("Tut!")
    }

    override fun wheeling() {
        println("Roue arrière en vélo!")
    }
}

interface Trick {
    fun wheeling()
}

interface Fuel {
    var fuelGauge: Float

    fun fillGasTank(){
        println("Remplissage du réservoir d'essence...")
        fuelGauge = 100f
    }

    fun displayGasGauge(){
        println("La jauge est à $fuelGauge")
    }
}

fun main(args: Array<String>) {
    var v: Vehicle = Car()
    if (v is Vehicle){
        print("v est un véhicule ")
        when(v){
            is Car -> println("de type Car")
            is Motorcycle -> println("de type Motorcycle")
            is Bicycle -> println("de type Bicycle")
        }
    }

    if (v is Bicycle){
        v.wheeling()
    }
    if (v is Trick){
        v.wheeling()
    }

//    val moto: Motorcycle = v as Motorcycle

    val moto: Motorcycle? = v as? Motorcycle
    moto?.fillGasTank()

    (v as? Motorcycle)?.fillGasTank()
    // Si vous êtes sûr et certain de vous alors :
    // (v as Motorcycle).fillGasTank()
}