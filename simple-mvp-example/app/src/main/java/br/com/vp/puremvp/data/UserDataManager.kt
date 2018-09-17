package br.com.vp.puremvp.data

import br.com.vp.puremvp.data.User
import br.com.vp.puremvp.welcome.WelcomeContract

/**
 * @author diegovidal on 12/06/2018.
 */
class UserDataManager: WelcomeContract.Model {

    private var user: User? = null

    override fun setDetails(firstName: String, lastName: String) {

        user = User(firstName, lastName)
    }

    override fun getWelcomeMessage(): String? {

        return "Welcome ${user?.firstName} ${user?.lastName}"
    }
}