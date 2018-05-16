package br.com.vp.advancedandroid.details

import br.com.vp.advancedandroid.base.ScreenModule
import br.com.vp.advancedandroid.details.RepoDetailsFragment.Companion.REPO_NAME_KEY
import br.com.vp.advancedandroid.details.RepoDetailsFragment.Companion.REPO_OWNER_KEY
import br.com.vp.advancedandroid.di.ScreenComponent
import br.com.vp.advancedandroid.di.ScreenScope
import dagger.BindsInstance
import dagger.Subcomponent
import dagger.android.AndroidInjector
import javax.inject.Named

/**
 * @author diegovidal on 01/05/2018.
 */

@ScreenScope
@Subcomponent(modules = [
    ScreenModule::class,
    RepoDetailsScreenModule::class
])
interface RepoDetailsComponent: ScreenComponent<RepoDetailsFragment> {

    @Subcomponent.Builder
    abstract class Builder: AndroidInjector.Builder<RepoDetailsFragment>(){

        @BindsInstance
        abstract fun bindRepoName(@Named("repo_name") repoName: String)

        @BindsInstance
        abstract fun bindRepoOwner(@Named("repo_owner") repoOwner: String)

        override fun seedInstance(instance: RepoDetailsFragment?) {

            instance?.arguments.let {
                bindRepoName(instance?.arguments?.getString(REPO_NAME_KEY) ?: "")
                bindRepoOwner(instance?.arguments?.getString(REPO_OWNER_KEY) ?: "")
            }
        }
    }
}