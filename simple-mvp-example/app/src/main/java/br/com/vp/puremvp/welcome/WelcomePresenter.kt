package br.com.vp.puremvp.welcome

/**
 * @author diegovidal on 12/06/2018.
 */
class WelcomePresenter(private val model: WelcomeContract.Model)
    : WelcomeContract.Presenter {

    lateinit var welcomeView: WelcomeContract.View

    override fun setView(view: WelcomeContract.View) {
        welcomeView = view
    }

    override fun loadWelcomeMessage() {

        welcomeView.showWelcomeMessage(model.getWelcomeMessage() ?: "NOT FOUND")
    }

    override fun submitName(firstName: String, lastName: String) {

        model.setDetails(firstName, lastName)
    }
}