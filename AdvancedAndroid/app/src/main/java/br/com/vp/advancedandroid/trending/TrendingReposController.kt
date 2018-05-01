package br.com.vp.advancedandroid.trending

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import br.com.vp.advancedandroid.R
import br.com.vp.advancedandroid.base.BaseController
import butterknife.BindView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * @author diegovidal on 18/04/2018.
 */

class TrendingReposController: BaseController() {

    @BindView(R.id.repo_list)
    lateinit var repoList: RecyclerView

    @BindView(R.id.loading_indicator)
    lateinit var loadingIndicator: ProgressBar

    @BindView(R.id.tv_error)
    lateinit var errorText: TextView

    @Inject
    lateinit var presenter: TrendingReposPresenter

    @Inject
    lateinit var viewModel: TrendingReposViewModel

    override fun onViewBound(view: View) {

        repoList.layoutManager = LinearLayoutManager(view.context)
        repoList.adapter = RepoAdapter(presenter)
    }

    override fun subscriptions(): Array<Disposable> {

        return arrayOf(
                viewModel.loading()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({loading ->
                    run {

                        loadingIndicator.visibility = if (loading) View.VISIBLE else View.GONE
                        repoList.visibility = if (loading) View.GONE else View.VISIBLE
                        errorText.visibility = if (loading) View.GONE else errorText.visibility
                    }
                }),

                viewModel.repos()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((repoList.adapter as RepoAdapter)::setData),

                viewModel.error()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({errorRes ->

                            if (errorRes == -1){
                                errorText.text = null
                                errorText.visibility = View.GONE
                            } else {

                                errorText.visibility = View.VISIBLE
                                repoList.visibility = View.GONE
                                errorText.setText(errorRes)
                            }
                        })

        )
    }

    override fun layoutRes(): Int {
        return R.layout.screen_trending_repos
    }
}