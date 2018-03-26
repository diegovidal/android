package oo

/**
 * Created by diegovidal on 14/03/2018.
 */

open class Being
open class Person: Being()
class Student: Person()

fun main(args: Array<String>) {

    // Covariance = we can use a "more derived" type (a subtype)

    val people: MutableList<Person> = mutableListOf(Person(), Person())
    people.add(Student()) // covariance

    // Being does not fulfill the contract of class Person
    //people.add(Being())

    val p: Any = Person() // covariance

    // Read-only collections are covariant
    val students: List<Person> = listOf(Student(), Student()) // covariance

    // val students2: MutableList<Person> = mutableListOf<Student>()
    // students2.add(Person())

    // Using Kotlin TimeSeries
    val events: TimeSeries<Person> = timeSeriesOf()
    val students3: List<Student> = listOf(Student(), Student())
    events.addAll(students3) // covariance

    // Using Java TimeSeries
    val chartData: JavaTimeSeries<Person> = JavaTimeSeries()
    chartData.add(Student()) // covariance
    chartData.addAll(students3)
}