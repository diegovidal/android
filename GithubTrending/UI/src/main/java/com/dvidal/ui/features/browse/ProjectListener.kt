package com.dvidal.ui.features.browse

/**
 * @author diegovidal on 02/10/2018.
 */

interface ProjectListener {

    fun onBookmarkedProjectClicked(projectId: String)

    fun onProjectClicked(projectId: String)

}