package oo

/**
 * Created by diegovidal on 08/03/2018.
 */

class Robot(val name: String) {

    fun greetHuman() {

        println("Hello Human, my name is $name")
    }

    fun knowsItsName(): Boolean {

        return name.isNotBlank()
    }
}



fun main(args: Array<String>) {

    val fightRobot = Robot("Destroyer9000")

    if (fightRobot.knowsItsName()) {

        fightRobot.greetHuman()
    }
}
