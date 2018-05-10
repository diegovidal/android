package br.com.vp.advancedandroid.ui

/**
 * @author diegovidal on 09/05/2018.
 */
interface TestInterface {

    fun clear()

    companion object {

        val DEFAULT: TestInterface = object : TestInterface {
            override fun clear() {
            }
        }
    }
}
