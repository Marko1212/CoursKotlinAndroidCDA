package com.formation.learningKotlin

open class Account {
    protected var balance: Int = 0

    open fun deposit(value: Int){
        balance += value
        println("$value € sur le compte. Nouveau solde : $balance €")
    }
}

class PositiveAccount : Account() {
    override fun deposit(value: Int) {
        if (!isValidDeposit(value)){
            println("Tu as fait une erreur. Opération négative interdite sur PositiveAccount")
            return
        }
        super.deposit(value)
    }

    // -100 + 50 = -50
    // -100 + 100 = 0
    private fun isValidDeposit(value: Int): Boolean {
        return balance + value >= 0
    }
}

fun main() {
    val account = Account()
    account.deposit(50)

    val posAccount = PositiveAccount()
    posAccount.deposit(20)
    posAccount.deposit(-10)
    posAccount.deposit(-100)
    posAccount.deposit(-10)
}