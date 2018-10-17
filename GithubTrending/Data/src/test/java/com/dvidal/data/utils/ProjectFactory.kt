package com.dvidal.data.utils

import com.dvidal.data.model.ProjectEntity
import com.dvidal.domain.model.Project

/**
 * @author diegovidal on 30/09/2018.
 */
object ProjectFactory {

    fun makeProjectEntity(): ProjectEntity {
        return ProjectEntity(DataFactory.randomString(),
                DataFactory.randomString(), DataFactory.randomString(),
                DataFactory.randomString(), DataFactory.randomString(),
                DataFactory.randomString(), DataFactory.randomString(),
                DataFactory.randomBoolean())
    }

    fun makeProject(): Project {
        return Project(DataFactory.randomString(),
                DataFactory.randomString(), DataFactory.randomString(),
                DataFactory.randomString(), DataFactory.randomString(),
                DataFactory.randomString(), DataFactory.randomString(),
                DataFactory.randomBoolean())
    }
}