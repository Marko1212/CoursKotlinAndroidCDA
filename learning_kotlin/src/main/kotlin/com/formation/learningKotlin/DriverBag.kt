package com.formation.learningKotlin

class Bag(itemsCount: Int){
    val items = arrayOfNulls<Item>(itemsCount)
    class Item(val weight: Int){
        fun showWeight(){
            println("le poids de l'item est de $weight")
        }
    }
}

fun main() {
    val bag = Bag(2)
    val firstItem = Bag.Item(5)
    firstItem.showWeight()

    bag.items[0] = firstItem
    bag.items[1] = Bag.Item(3)


}