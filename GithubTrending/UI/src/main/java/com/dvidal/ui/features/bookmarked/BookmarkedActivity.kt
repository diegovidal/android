package com.dvidal.ui.features.bookmarked

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import com.dvidal.presentation.BrowseBookmarkedProjectsViewModel
import com.dvidal.presentation.model.ProjectView
import com.dvidal.presentation.state.Resource
import com.dvidal.presentation.state.ResourceState
import com.dvidal.ui.R
import com.dvidal.ui.di.ViewModelFactory
import com.dvidal.ui.mapper.ProjectViewMapper
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_bookmarked.*
import timber.log.Timber
import javax.inject.Inject

/**
 * @author diegovidal on 02/10/2018.
 */
class BookmarkedActivity: AppCompatActivity() {

    @Inject lateinit var adapter: BookmarkedAdapter
    @Inject lateinit var mapper: ProjectViewMapper
    @Inject lateinit var viewModelFactory: ViewModelFactory

    private lateinit var browseViewModel: BrowseBookmarkedProjectsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmarked)
        AndroidInjection.inject(this)

        browseViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(BrowseBookmarkedProjectsViewModel::class.java)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupBrowseRecycler()
    }

    override fun onStart() {
        super.onStart()
        browseViewModel.getBookmarkedProjects().observe(this, Observer {
            it?.let {
                handleDataState(it)
            }
        })
        browseViewModel.fetchBookmarkedProjects()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId){
            android.R.id.home -> {
                finish()
                true
            } else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupBrowseRecycler() {
        recycler_projects.layoutManager = LinearLayoutManager(this)
        recycler_projects.adapter = adapter
    }

    private fun handleDataState(resource: Resource<List<ProjectView>>) {

        when (resource.status){
            ResourceState.LOADING -> {
                progress.visibility = View.VISIBLE
                recycler_projects.visibility = View.GONE
            }
            ResourceState.SUCCESS -> {
                progress.visibility = View.GONE
                recycler_projects.visibility = View.VISIBLE
                resource.data?.let {
                    adapter.projects = it.map { mapper.mapToView(it) }
                    adapter.notifyDataSetChanged()
                }
            }
            ResourceState.ERROR -> {
                Timber.d("ERROR HERE!")
            }
        }
    }

    companion object {

        fun getStartIntent(context: Context): Intent {
            return Intent(context, BookmarkedActivity::class.java)
        }
    }
}