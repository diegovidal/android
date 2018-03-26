package oo

/**
 * Created by diegovidal on 13/03/2018.
 */

fun Bicycle.replaceWhell() {
    println("Replacing wheel...")
}

fun Boat.startEngine(): Boolean {

    println("Starting engine...")
    return true
}

fun main(args: Array<String>) {

    val vehicle: Drivable = Bicycle()

    vehicle.drive()

    // instanceof <-> is (SMART CAST)
    if (vehicle is Bicycle) {

        vehicle.replaceWhell()
    } else if (vehicle is Boat) {

        vehicle.startEngine()
    }

    if (vehicle is Boat && vehicle.startEngine()) {
        // ...
    }

    if (vehicle !is Boat || vehicle.startEngine()) {
        // ...
    }

    if (vehicle !is Bicycle) {
        return
    }

    vehicle.replaceWhell()
}