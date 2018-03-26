package basics

import java.util.*

/**
 * Created by diegovidal on 26/02/2018.
 */

val PI = 3.1415

fun main(args: Array<String>) {

    val list = listOf("Kotlin", "Android")
    println(list)
    println(PI)

    collectionExercise()
}

fun verifyUser() {

    val input = readLine() ?: "Nada"

    var output = when(input){

        "" -> "Campo está vazio"
        else -> "Muito bem vindo $input"
    }

    print(output)
}

fun collectionExercise(){

    var list: MutableList<Int> = mutableListOf()
    var randomGen = Random()

    for (i in 1..100) {

        var randomNumb = (randomGen.nextInt(100)) + 1
        list.add(randomNumb)
    }

    println(list.joinToString())
}




