package functional

/**
 * Created by diegovidal on 06/03/2018.
 */

inline fun modifyString(str: String, operation: (String) -> String): String {

    return operation(str)
}

fun main(args: Array<String>) {

    modifyString("Kotlin is amazing", { it.toUpperCase() })
}