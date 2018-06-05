package br.com.vp.findtheday

/**
 * @author diegovidal on 05/06/2018.
 */
class DayChooser(private val givenIndex: Int = 0,
                 private var selectedDay: String? = null) {

    fun getTheDay(index: Int): String? {

        val reminder = index % 5
        when (reminder) {
            1 -> selectedDay = "Monday"
            2 -> selectedDay = "Tuesday"
            3 -> selectedDay = "Wednesday"
            4 -> selectedDay = "Thursday"
            0 -> selectedDay = "Friday"
        }

        return selectedDay
    }


}
