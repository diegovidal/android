package functional

/**
 * Created by diegovidal on 06/03/2018.
 */

fun main(args: Array<String>) {

    val props = System.getProperties()

//    props.list(System.out)
//    props.propertyNames().toList()

    with(props) {

        list(System.out)
        println(propertyNames().toList())
        println(getProperty("user.home"))
    }
}