package com.dvidal.remote.mapper

import com.dvidal.data.model.ProjectEntity
import com.dvidal.remote.model.ProjectModel
import javax.inject.Inject

/**
 * @author diegovidal on 01/10/2018.
 */

class ProjectsResponseModelMapper @Inject constructor(): ModelMapper<ProjectModel, ProjectEntity> {

    override fun mapFromModel(model: ProjectModel): ProjectEntity {
        return ProjectEntity(model.id, model.name, model.fullName, model.starCount.toString(),
                model.dateCreated, model.owner.ownerName, model.owner.ownerAvatar, false)
    }

}