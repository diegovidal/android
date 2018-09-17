package br.com.vp.advancedandroid.details

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
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
 * @author diegovidal on 01/05/2018.
 */
class RepoDetailsFragment: BaseFragment(){

    @BindView(R.id.tv_repo_name) lateinit var repoNameText: TextView
    @BindView(R.id.tv_repo_description) lateinit var repoDescriptionText: TextView
    @BindView(R.id.tv_creation_date) lateinit var createdDateText: TextView
    @BindView(R.id.tv_updated_date) lateinit var updatedDateText: TextView
    @BindView(R.id.contributor_list) lateinit var contributorList: RecyclerView
    @BindView(R.id.loading_indicator) lateinit var detailsLoadingView: View
    @BindView(R.id.contributor_loading_indicator) lateinit var contributorsLoadingView: View
    @BindView(R.id.content) lateinit var contentContainer: View
    @BindView(R.id.tv_error) lateinit var errorText: TextView
    @BindView(R.id.tv_contributors_error) lateinit var contributorsErrorText: TextView

    @Inject lateinit var viewModel: RepoDetailsViewModel
    @Inject lateinit var presenter: RepoDetailsPresenter
    @Inject lateinit var dataSource: RecyclerDataSource

    override fun onViewBound(view: View) {
        contributorList.layoutManager = LinearLayoutManager(view.context)
        contributorList.adapter = RecyclerAdapter(dataSource)
    }

    override fun subscriptions(): Array<Disposable> {

        return arrayOf(
            viewModel.details()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe( { details ->

                        if (details.loading){
                            detailsLoadingView.visibility = View.VISIBLE
                            contentContainer.visibility = View.GONE
                            errorText.visibility = View.GONE
                            errorText.text = null
                        } else {
                            errorText.setText(details?.errorRes ?: R.string.api_error_contributors)
                            detailsLoadingView.visibility = View.GONE
                            contentContainer.visibility = if (details.isSuccess()) View.VISIBLE else View.GONE
                            errorText.visibility = if (details.isSuccess()) View.GONE else View.VISIBLE

                            repoNameText.text = details?.name
                            repoDescriptionText.text = details?.description
                            createdDateText.text = details?.createdDate
                            updatedDateText.text = details?.updatedDate
                        }
                    }),

            viewModel.contributors()
                     .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({contributorsDetails ->

                        if (contributorsDetails.loading){
                            contributorsLoadingView.visibility = View.VISIBLE
                            contributorList.visibility = View.GONE
                            contributorsErrorText.visibility = View.GONE
                            contributorsErrorText.text = null
                        } else {

                            contributorsLoadingView.visibility = View.GONE
                            contributorList.visibility = if (contributorsDetails.isSuccess()) View.VISIBLE else View.GONE
                            contributorsErrorText.visibility = if (contributorsDetails.isSuccess()) View.GONE else View.VISIBLE

                            if (contributorsDetails.isSuccess()) {

                                contributorsErrorText.text = null
                            } else {

                                contributorsErrorText.setText(contributorsDetails?.errorRes ?: R.string.api_error_contributors)
                            }
                        }
                    })
        )
    }

    override fun layoutRes(): Int {
        return R.layout.screen_repo_details
    }

    companion object {

        const val REPO_NAME_KEY = "repo_name"
        const val REPO_OWNER_KEY = "repo_owner"

        fun newInstance(repoName: String, repoOwner: String): Fragment {

            val bundle = Bundle()
            bundle.putString(REPO_NAME_KEY, repoName)
            bundle.putString(REPO_OWNER_KEY, repoOwner)
            bundle.putString("instance_id", UUID.randomUUID().toString())

            val fragment = RepoDetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}