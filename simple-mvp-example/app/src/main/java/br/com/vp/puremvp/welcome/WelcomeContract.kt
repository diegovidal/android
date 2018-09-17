package br.com.vp.puremvp.welcome

/**
 * @author diegovidal on 12/06/2018.
 */

interface WelcomeContract {

    interface View {

        fun showWelcomeMessage(message: String)
    }

    interface Model {

        fun setDetails(firstName: String, lastName: String)

        fun getWelcomeMessage(): String?
    }

    interface Presenter {

        fun setView(view: View)

        fun loadWelcomeMessage()

        fun submitName(firstName: String, lastName: String)
    }
}