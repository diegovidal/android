package com.dvidal.presentation.mapper

import com.dvidal.domain.model.Project
import com.dvidal.presentation.model.ProjectView
import javax.inject.Inject

/**
 * @author diegovidal on 01/10/2018.
 */
class ProjectViewMapper @Inject constructor(): Mapper<ProjectView, Project> {

    override fun mapToView(type: Project): ProjectView {
        return ProjectView(type.id, type.name, type.fullName,
                type.starCount, type.dateCreated, type.ownerName,
                type.ownerAvatar, type.isBookmarked)
    }
}