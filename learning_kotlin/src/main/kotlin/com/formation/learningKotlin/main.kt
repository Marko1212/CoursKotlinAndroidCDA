package com.formation.learningKotlin

abstract class Vehicle(val wheelsCount: Int) {
    fun showWheels(){
        println("Nombre de roues : $wheelsCount")
    }
    abstract fun honk()
    companion object {
        fun createCar() = Car(4)
    }
}

class Car(wheelsCount: Int) : Vehicle(wheelsCount), Fuel {
    override var fuelGauge: Float = 0f

    inner class Engine {
        fun displayHorsePower() {
            println("La voiture a ${wheelsCount * 3} chevaux")
        }
    }

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
    val car = Vehicle.createCar()

}