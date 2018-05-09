package br.com.vp.advancedandroid.details

import br.com.vp.advancedandroid.details.RepoDetailsController.Companion.REPO_NAME_KEY
import br.com.vp.advancedandroid.details.RepoDetailsController.Companion.REPO_OWNER_KEY
import br.com.vp.advancedandroid.di.ScreenScope
import dagger.BindsInstance
import dagger.Subcomponent
import dagger.android.AndroidInjector
import javax.inject.Named

/**
 * @author diegovidal on 01/05/2018.
 */

@ScreenScope
@Subcomponent
interface RepoDetailsComponent: AndroidInjector<RepoDetailsController> {

    @Subcomponent.Builder
    abstract class Builder: AndroidInjector.Builder<RepoDetailsController>(){

        @BindsInstance
        abstract fun bindRepoName(@Named("repo_name") repoOwner: String)

        @BindsInstance
        abstract fun bindRepoOwner(@Named("repo_owner") repoOwner: String)

        override fun seedInstance(instance: RepoDetailsController?) {

            instance?.args?.let {
                bindRepoName(instance.args.getString(REPO_NAME_KEY))
                bindRepoOwner(instance.args.getString(REPO_OWNER_KEY))
            }
        }
    }
}