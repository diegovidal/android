package br.com.vp.advancedandroid.database.favorites

import br.com.vp.advancedandroid.database.AppDatabase
import br.com.vp.advancedandroid.model.Contributor
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author diegovidal on 16/05/2018.
 */

@Singleton
class FavoriteService @Inject
    constructor(val appDatabase: AppDatabase) {

    private val favoritedContributorsIdsRelay: BehaviorRelay<Set<Long>> = BehaviorRelay.createDefault(hashSetOf())
    
    init {
        
        appDatabase.favoriteContributorDao().getFavoritedContributors()
                .subscribeOn(Schedulers.io())
                .map { favoritedContributors ->
                    val contributorId = hashSetOf<Long>()
                    for (fc in favoritedContributors){
                        contributorId.add(fc.id)
                    }
                    return@map contributorId
                }
                .subscribe(favoritedContributorsIdsRelay, Consumer { t ->
                    Timber.e(t ,"Error loading favorited contributors from database")
                })
    }

    fun favoritedContributorsIds(): Observable<Set<Long>> {
        return favoritedContributorsIdsRelay
    }

    fun toggleFavoriteContributor(contributor: Contributor){
        runDbOp(Action {
            if (favoritedContributorsIdsRelay.value.contains(contributor.id)){
                deleteFavoriteContributor(contributor)
            } else {
                addFavoriteContributor(contributor)
            }
        })
    }

    private fun addFavoriteContributor(contributor: Contributor) {
        appDatabase.favoriteContributorDao().addFavorite(FavoriteContributor(contributor.id))
    }

    private fun deleteFavoriteContributor(contributor: Contributor) {
        appDatabase.favoriteContributorDao().deleteFavorite(FavoriteContributor(contributor.id))
    }

    private fun runDbOp(action: Action){
        Completable.fromAction(action)
                .subscribeOn(Schedulers.io())
                .subscribe({}, {t ->
                    Timber.e("Error loading favorited contributors from database")
                })
    }
}