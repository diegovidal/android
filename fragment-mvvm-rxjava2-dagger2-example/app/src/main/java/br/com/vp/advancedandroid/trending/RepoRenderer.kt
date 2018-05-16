package br.com.vp.advancedandroid.trending

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.vp.advancedandroid.R
import br.com.vp.advancedandroid.model.Repo
import br.com.vp.advancedandroid.poweradapter.item.ItemRenderer
import butterknife.BindView
import butterknife.ButterKnife
import java.text.NumberFormat
import javax.inject.Inject
import javax.inject.Provider

/**
 * @author diegovidal on 14/05/2018.
 */
class RepoRenderer @Inject
        constructor(private val presenterProvider: Provider<TrendingReposPresenter>)
    : ItemRenderer<Repo> {

    override fun layoutRes(): Int {
        return R.layout.view_repo_list_item
    }

    override fun createView(parent: ViewGroup): View {
        val view = LayoutInflater.from(parent.context).inflate(layoutRes(), parent, false)
        view.tag = ViewBinder(view, presenterProvider.get())
        return view
    }

    override fun render(itemView: View, item: Repo) {
        (itemView.tag as? ViewBinder)?.bind(item)
    }

    internal class ViewBinder(itemView: View,
                              presenter: TrendingReposPresenter) {

        @BindView(R.id.tv_repo_name) lateinit var repoNameText: TextView
        @BindView(R.id.tv_repo_description) lateinit var repoDescriptionText: TextView
        @BindView(R.id.tv_fork_count) lateinit var forkCountText: TextView
        @BindView(R.id.tv_star_count) lateinit var starCountText: TextView

        private var repo: Repo? = null

        init {
            ButterKnife.bind(this, itemView)
            itemView.setOnClickListener { v ->
                repo?.let {
                    presenter.onRepoClicked(it)
                }
            }
        }

        fun bind(repo: Repo) {

            this.repo = repo
            repoNameText.text = repo.name
            repoDescriptionText.text = repo.description
            forkCountText.text = NumberFormat.getInstance().format(repo.forksCount)
            starCountText.text = NumberFormat.getInstance().format(repo.stargazersCount)
        }
    }
}