package com.dvidal.ui.utils

import com.dvidal.presentation.model.ProjectView

/**
 * @author diegovidal on 02/10/2018.
 */
object TestProjectFactory {

    fun makeProjectView(): ProjectView {
        return ProjectView(TestDataFactory.randomUuid(), TestDataFactory.randomUuid(),
                TestDataFactory.randomUuid(), TestDataFactory.randomUuid(), TestDataFactory.randomUuid(),
                TestDataFactory.randomUuid(), TestDataFactory.randomUuid(), TestDataFactory.randomBoolean())
    }
}