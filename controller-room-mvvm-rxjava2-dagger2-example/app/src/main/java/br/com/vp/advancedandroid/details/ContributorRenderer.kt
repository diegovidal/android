package br.com.vp.advancedandroid.details

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.vp.advancedandroid.R
import br.com.vp.advancedandroid.database.favorites.FavoriteContributor
import br.com.vp.advancedandroid.database.favorites.FavoriteService
import br.com.vp.advancedandroid.model.Contributor
import br.com.vp.advancedandroid.poweradapter.item.ItemRenderer
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnLongClick
import com.bumptech.glide.Glide
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * @author diegovidal on 15/05/2018.
 */
class ContributorRenderer @Inject
        constructor(private val favoriteService: FavoriteService)
    : ItemRenderer<Contributor> {

    override fun layoutRes(): Int {

        return R.layout.view_user_list_item
    }

    override fun createView(parent: ViewGroup): View {
        val view = LayoutInflater.from(parent.context).inflate(layoutRes(), parent, false)
        view.tag = ViewBinder(view, favoriteService)
        return view
    }

    override fun render(itemView: View, item: Contributor) {
        (itemView.tag as? ViewBinder)?.bind(item)
    }

    internal class ViewBinder(itemView: View, val favoriteService: FavoriteService) {

        @BindView(R.id.tv_user_name) lateinit var usernameText: TextView
        @BindView(R.id.iv_avatar) lateinit var avatarImageView: ImageView
        @BindView(R.id.parent_view) lateinit var parentView: View

        private var contributor: Contributor? = null
        private var favoriteDisposable: Disposable? = null

        init {

            ButterKnife.bind(this, itemView)
            RxView.attachEvents(parentView)
                    .subscribe({ event ->

                        if (event.view().isAttachedToWindow) {
                            listenForFavoriteChanges()
                        } else {
                            favoriteDisposable?.let {
                                it.dispose()
                                favoriteDisposable = null
                            }
                        }
                    })
        }

        @OnLongClick(R.id.parent_view)
        fun toggleFavorite(): Boolean{

            contributor?.let {
                favoriteService.toggleFavoriteContributor(it)
            }
            return true
        }

        private fun listenForFavoriteChanges() {
            favoriteDisposable = favoriteService.favoritedContributorsIds()
                    .filter({_ -> contributor != null })
                    .map { containsContributor(it, contributor!!)}
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({isFavorite ->
                        parentView.setBackgroundColor( if (isFavorite) Color.YELLOW else Color.TRANSPARENT )
                    })
        }

        private fun containsContributor(contributors: List<FavoriteContributor>, contributor: Contributor): Boolean {

            for (c in contributors){
                if (c.id == contributor.id)
                    return true
            }
            return false
        }

        fun bind(contributor: Contributor){

            this.contributor = contributor
            usernameText.text = contributor.login
            Glide.with(avatarImageView.context)
                    .load(contributor.avatarUrl)
                    .into(avatarImageView)
        }
    }
}