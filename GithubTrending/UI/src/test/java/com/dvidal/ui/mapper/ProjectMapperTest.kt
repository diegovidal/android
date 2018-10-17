package com.dvidal.ui.mapper

import com.dvidal.ui.utils.ProjectFactory
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * @author diegovidal on 02/10/2018.
 */

@RunWith(JUnit4::class)
class ProjectMapperTest {

    private val projectMapper = ProjectViewMapper()

    @Test
    fun mapToViewMapsData() {
        val project = ProjectFactory.makeProjectView()
        val projectForUi = projectMapper.mapToView(project)

        assertEquals(project.id, projectForUi.id)
        assertEquals(project.name, projectForUi.name)
        assertEquals(project.fullName, projectForUi.fullName)
        assertEquals(project.starCount, projectForUi.starCount)
        assertEquals(project.dateCreated, projectForUi.dateCreated)
        assertEquals(project.ownerName, projectForUi.ownerName)
        assertEquals(project.ownerAvatar, projectForUi.ownerAvatar)
        assertEquals(project.isBookmarked, projectForUi.isBookmarked)
    }
}