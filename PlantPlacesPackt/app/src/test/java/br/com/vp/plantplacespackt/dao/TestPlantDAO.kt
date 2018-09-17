package br.com.vp.plantplacespackt.dao

import junit.framework.Assert.assertTrue
import org.junit.*

/**
 * @author diegovidal on 29/08/2018.
 */

class TestPlantDAO {

    // define a variable for the DAO we are testing
    lateinit var plantDAO: IPlantDAO

    @Before
    fun setup(){

        plantDAO = PlantDAOStub()
        println("Before: running init for EACH tests.")
    }

    @Test
    fun testPlantDAO_searchForRedbudShouldReturnAtLeastOneResult() {

        // assume we do not have a match
        var redbudFound = false

        val plants = plantDAO.fetchPlants("Redbud")
        for (plant in plants){
            if (plant.common.contains("Redbud")){
                redbudFound = true
            }
        }

        // did we find a redbud?
        assertTrue(redbudFound)
        println("TEST: running redbud test.")
    }

    @Test
    fun testPlantDAO_searchForOakShouldReturnAtLeastOneWhiteOak() {

        // assume we do not have a match
        var whiteOakFound = false

        val plants = plantDAO.fetchPlants("Oak")
        for (plant in plants){
            if (plant.genus.contains("Quercus", true) && plant.species.contains("alba")){
                whiteOakFound = true
            }
        }

        // did we find a redbud?
        assertTrue(whiteOakFound)
        println("TEST: running white oak test.")
    }

    fun testPlantDAO_searchForEachShouldReturnAtLeastTwoResults() {

        val plants = plantDAO.fetchPlants("e")
        val size = plants.size
        val atLeastTwo = size > 2

        assertTrue(atLeastTwo)
    }

    @After
    fun teardown(){
        println("After: tearing down EACH tests.")
    }

    companion object {

        @JvmStatic
        @BeforeClass
        fun setupAllTests(){
            println("BeforeClass: running init for ALL tests.")
        }

        @JvmStatic
        @AfterClass
        fun teardownAllTests(){
            println("AfterClass: tearing down after ALL tests.")
        }
    }
}