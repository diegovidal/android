package com.dvidal.domain.utils

import com.dvidal.domain.model.Project

/**
 * @author diegovidal on 30/09/2018.
 */
object ProjectDataFactory {

    fun randomUuid(): String {
        return java.util.UUID.randomUUID().toString()
    }

    private fun makeProject(): Project {
        return Project(randomUuid(), randomUuid(), randomUuid(), randomUuid(),
                randomUuid(), randomUuid(), randomUuid(), false)
    }

    fun makeProjectList(count: Int) : List<Project> {
        val projects = mutableListOf<Project>()
        repeat(count) {
            projects.add(makeProject())
        }
        return projects
    }
}