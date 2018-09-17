package br.com.vp.advancedandroid.trending

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import br.com.vp.advancedandroid.R
import br.com.vp.advancedandroid.base.BaseFragment
import br.com.vp.advancedandroid.poweradapter.adapter.RecyclerAdapter
import br.com.vp.advancedandroid.poweradapter.adapter.RecyclerDataSource
import butterknife.BindView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.*
import javax.inject.Inject

/**
 * @author diegovidal on 18/04/2018.
 */

class TrendingReposFragment: BaseFragment() {

    @BindView(R.id.repo_list) lateinit var repoList: RecyclerView
    @BindView(R.id.loading_indicator) lateinit var loadingIndicator: ProgressBar
    @BindView(R.id.tv_error) lateinit var errorText: TextView

    @Inject lateinit var presenter: TrendingReposPresenter
    @Inject lateinit var viewModel: TrendingReposViewModel
    @Inject lateinit var dataSource: RecyclerDataSource

    override fun onViewBound(view: View) {

        repoList.layoutManager = LinearLayoutManager(view.context)
        repoList.adapter = RecyclerAdapter(dataSource)
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

    companion object {

        fun newInstance(): Fragment {

            val bundle = Bundle()
            bundle.putString("instance_id", UUID.randomUUID().toString())

            val fragment = TrendingReposFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}