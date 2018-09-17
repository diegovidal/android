package br.com.vp.advancedandroid.ui


/**
 * @author diegovidal on 23/04/2018.
 */

interface ScreenNavigator {

    fun pop(): Boolean?

    fun goToRepoDetails(repoOwner: String, repoName: String)
}