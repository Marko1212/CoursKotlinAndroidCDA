
    import java.util.*

    fun main(args: Array<String>) {
        val reader = Scanner(System.`in`)
        print("Entrez deux nombres : ")


        val premierNombre = reader.nextDouble()
        val secondNombre = reader.nextDouble()

        print("Entrez un opérateur (+, -, *, /, %): ")
        val operateur = reader.next()[0]

        val resultat: Double

        when (operateur) {
            '+' -> resultat = premierNombre + secondNombre
            '-' -> resultat = premierNombre - secondNombre
            '*' -> resultat = premierNombre * secondNombre
            '/' -> resultat = premierNombre / secondNombre
            '%' -> resultat = premierNombre % secondNombre

            else -> {
                System.out.printf("Erreur! L'opérateur n'est pas correct!")
                return
            }
        }

        System.out.printf("%.1f %c %.1f = %.1f", premierNombre, operateur, secondNombre, resultat)
        reader.close()
    }

    /* CORRECTION

    fun main(args: Array<String>) {
        println("Bienvenue dans la calculatrice")
        println("""Choisis l'opération avec le nombre indiqué devant :
        |    1. Addition
        |    2. Soustraction
        |    3. Multiplication
        |    4. Division
        |    5. Reste de la division
    """.trimMargin())
        val op = readLine()?.toInt()
        println("Rentre un premier nombre :")
        val premier = readLine()?.toFloat()
        println("Rentre un second nombre :")
        val second = readLine()?.toFloat()

        if (premier != null && second != null && op != null){
            val resultat = when(op){
                1 -> premier + second
                2 -> premier - second
                3 -> premier * second
                4 -> premier / second
                5 -> premier % second
                else -> 0
            }
            val ope = arrayOf("+", "-", "*", "/", "%")
            println("Le résultat de : $premier ${ope[op-1]} $second est $resultat")
        }else{
            println("Rentre moi de bonnes informations!! Recommence")
        }
    }


     */