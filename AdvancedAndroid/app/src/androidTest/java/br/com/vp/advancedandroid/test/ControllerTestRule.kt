package br.com.vp.advancedandroid.test

import android.app.Activity
import android.support.test.rule.ActivityTestRule
import br.com.vp.advancedandroid.base.TestApplication
import br.com.vp.advancedandroid.data.RepoRepository
import br.com.vp.advancedandroid.data.TestRepoService
import br.com.vp.advancedandroid.ui.TestScreenNavigator

/**
 * @author diegovidal on 08/05/2018.
 */
class ControllerTestRule<T : Activity>(activityClass: Class<T>)
    : ActivityTestRule<T>(activityClass, true, false) {

    val testScreenNavigator = TestApplication.getComponent()?.screenNavigator()
    val testRepoService = TestApplication.getComponent()?.repoService()
    val repoRepository = TestApplication.getComponent()?.repoRepository()

    fun clearState(){

        testRepoService?.clearErrorFlags()
        testRepoService?.clearHoldFlags()
        repoRepository?.clearCache()
    }
}
