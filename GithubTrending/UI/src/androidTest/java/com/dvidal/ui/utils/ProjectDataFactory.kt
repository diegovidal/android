package com.dvidal.ui.utils

import com.dvidal.domain.model.Project


/**
 * @author diegovidal on 02/10/2018.
 */

object ProjectDataFactory {

    fun makeProject(): Project {
        return Project(TestDataFactory.randomUuid(), TestDataFactory.randomUuid(),
                TestDataFactory.randomUuid(), TestDataFactory.randomUuid(), TestDataFactory.randomUuid(),
                TestDataFactory.randomUuid(), TestDataFactory.randomUuid(), TestDataFactory.randomBoolean())
    }

}