package com.dvidal.ui.mapper

import com.dvidal.presentation.model.ProjectView
import com.dvidal.ui.model.Project
import javax.inject.Inject

/**
 * @author diegovidal on 02/10/2018.
 */
class ProjectViewMapper @Inject constructor(): ViewMapper<ProjectView, Project> {

    override fun mapToView(presentation: ProjectView): Project {
        return Project(presentation.id, presentation.name,
                presentation.fullName, presentation.starCount,
                presentation.dateCreated, presentation.ownerName,
                presentation.ownerAvatar, presentation.isBookmarked)
    }
}