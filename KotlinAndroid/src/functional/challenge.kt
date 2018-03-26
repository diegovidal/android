package functional

/**
 * Created by diegovidal on 05/03/2018.
 */

fun main(args: Array<String>) {

    // Some faulty data with ages of our users
    val data = mapOf(
            "users1.csv" to listOf(32, 45, 17, -1, 34),
            "users2.csv" to listOf(19, -1, 67, 22),
            "users3.csv" to listOf(),
            "users4.csv" to listOf(56, 32, 18, 44)
    )

    firstChallenge(data)
}

fun firstChallenge(data: Map<String, List<Int>>){

    val listInt = data.flatMap { it.value }

    val justPositiveInt = listInt.filter { it >= 0 }

    println(listInt)
    println(justPositiveInt)
    println("average: ${justPositiveInt.average()}")
}

fun secondChallenge(data: Map<String, List<Int>>){

    var listFaultyData = data.flatMap { it.value.filter { it <= 0 } }
    var answer = data.filter { it.value.containsAll(listFaultyData)}.map { it.key }

    println(answer)
}

fun thirdChallenge(data: Map<String, List<Int>>){

    var listFaultyData = data.flatMap { it.value.filter { it <= 0 } }

    println("falty entries size: ${listFaultyData.size}")
}

fun solutions(data: Map<String, List<Int>>) {

    // 1) Average age of users
    val averageAge = data.flatMap { it.value }
            .filter { it >= 0 }
            .average()

    // 2) Files with faulty data
    val faultyFiles = data.filter { it.value.any { it < 0 } }
            .map { it.key }

    // 3) Number of faulty entries
    val numberOfFaults = data.flatMap { it.value }
            .filter { it < 0 }
            .count()


    println("Users average %.2f years of age.".format(averageAge))
    println("Files with faulty data: $faultyFiles")
    println("There were $numberOfFaults errors in the data.")
}