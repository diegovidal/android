package com.dvidal.creaturemon.model

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * @author diegovidal on 06/12/18.
 */
class CreatureGeneratorTest {

    private lateinit var creatureGenerator: CreatureGenerator

    @Before
    fun setup() {

        creatureGenerator = CreatureGenerator()
    }

    @Test
    fun testGenerateHitPoints() {

        val attributes = CreatureAttributes(
                intelligence = 7,
                strength = 3,
                endurance = 10
        )

        val name = "Monsterino"
        val expectedCreature = Creature(attributes, 84, name)

        assertEquals(expectedCreature, creatureGenerator.generateCreature(attributes, name))
    }
}