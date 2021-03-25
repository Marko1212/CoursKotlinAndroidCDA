
open class Pokemon(val nom: String, var hp: Float, val atk: Int) {

    fun isDead(): Boolean = this.hp <= 0

    open fun attaquer(p: Pokemon) {
        p.hp -= atk
    }

    override fun toString(): String {
        return "$nom a $hp hp et a $atk comme atk"
    }

}

 class PokemonFeu(nom: String, hp: Float, atk: Int): Pokemon(nom, hp, atk) {

     override fun attaquer (p: Pokemon) {
         when(p) {
             is PokemonPlante -> p.hp -= atk*2
             is PokemonEau -> p.hp -= this.atk * 0.5f
             is PokemonFeu -> p.hp -= this.atk * 0.5f
             else -> super.attaquer(p)
         }
     }

 }

class PokemonEau(nom: String, hp: Float, atk: Int): Pokemon(nom, hp, atk) {

    override fun attaquer (p: Pokemon) {
        when(p) {
            is PokemonPlante -> p.hp -= atk*0.5f
            is PokemonEau -> p.hp -= this.atk * 0.5f
            is PokemonFeu -> p.hp -= this.atk * 2
            else -> super.attaquer(p)
        }
    }

}

class PokemonPlante(nom: String, hp: Float, atk: Int): Pokemon(nom, hp, atk) {

    override fun attaquer (p: Pokemon) {
        when(p) {
            is PokemonPlante -> p.hp -= atk*0.5f
            is PokemonEau -> p.hp -= this.atk * 2
            is PokemonFeu -> p.hp -= this.atk * 0.5f
            else -> super.attaquer(p)
        }
    }

}

fun main(args: Array<String>) {

    val salameche = PokemonFeu("salameche", 100f, 13)
    val carapuce = PokemonEau("carapuce", 100f, 14)
    val bulbizarre = PokemonPlante("bulbizarre", 100f, 12)
    val pikachu = Pokemon("pikachu", 100f, 13)

    var count = 1
    while(!salameche.isDead() && !carapuce.isDead()) {
        if (count++ % 2 == 0) {
            carapuce.attaquer(salameche)
        } else {
            salameche.attaquer(carapuce)
        }
    }

    println(carapuce)
    println(salameche)

    if (salameche.isDead()) {
        println("Carapuce a gagné")
    }  else {
        println("Salameche a gagné")
    }

}