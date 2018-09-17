package br.com.vp.plantplacespackt.dao

import br.com.vp.plantplacespackt.dao.IPlantDAO
import br.com.vp.plantplacespackt.dao.PlantDAO
import br.com.vp.plantplacespackt.dto.PlantDTO
import junit.framework.Assert.*
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Test

/**
 * @author diegovidal on 29/08/2018.
 */
class BDDTestPlantDAO {

    private lateinit var plantDAO: IPlantDAO
    private lateinit var plants: List<PlantDTO>

    @Test
    fun testPlantDAO_fetchShouldReturnResultsForRedbud() {

        givenPlantDAOISInitialized()
        whenSearchForRedbud()
        thenVerifyAtLeastOneCercisCanadensis()
    }

    private fun givenPlantDAOISInitialized() {

        plantDAO = PlantDAO()
    }

    private fun whenSearchForRedbud() {

        plants = plantDAO.fetchPlants("Redbud")
    }

    private fun thenVerifyAtLeastOneCercisCanadensis() {

        // assume we do not have a match
        var redbudFound = false
        for (plant in plants){
            if (plant.genus.contains("Cercis", true) && plant.species.contains("canadensis", true)){
                redbudFound = true
                break
            }
        }

        // did we find a redbud?
        assertTrue(redbudFound)
        println("TEST: running redbud test.")
    }

    // ◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊

    @Test
    fun testPlantDAO_fetchShouldReturnAtLeastTwoOaksForQuercus() {

        givenPlantDAOISInitialized()
        whenSearchForQuercus()
        thenVerifyTwoOaks()
    }

    private fun whenSearchForQuercus() {

        plants = plantDAO.fetchPlants("Quercus")
    }

    private fun thenVerifyTwoOaks() {

        // assume we do not have a match
        var quercusRoburFound = false
        for (plant in plants){
            if (plant.genus.contains("Quercus", true) && plant.species.contains("robur", true) &&
                    plant.common.contains("Oak", true)){
                quercusRoburFound = true
            }
        }

        // did we find a robur?
        assertTrue(quercusRoburFound)

        // assume we do not have a match
        var quercusAlbaFound = false
        for (plant in plants){
            if (plant.genus.contains("Quercus", true) && plant.species.contains("alba", true) &&
                    plant.common.contains("Oak", true)){
                quercusAlbaFound = true
            }
        }

        // did we find a alba?
        assertTrue(quercusAlbaFound)
        println("TEST: running two oaks test.")
    }

    // ◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊

    @Test
    fun testPlantDAO_fetchShouldReturnNothingForGibberish() {

        givenPlantDAOISInitialized()
        whenSearchForGibberish()
        thenVerifyNoResults()
    }

    private fun whenSearchForGibberish() {

        plants = plantDAO.fetchPlants("sklujapouetllkjsda")
    }

    private fun thenVerifyNoResults() {

//        val size = plants.size
//        assertEquals(0, size)

        // hamcrest
        assertThat(plants, empty())

        println("TEST: running gibberish test.")
    }

    // ◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊

    @Test
    fun testPlantDAO_fetchShouldReturnGenusQuercusForQuercus() {

        givenPlantDAOISInitialized()
        whenSearchForQuercus()
        thenVerifyAllGenusAreQuercus()
    }

    private fun thenVerifyAllGenusAreQuercus() {

        for (plant in plants){
            //assertEquals("Quercus", plant.genus)

            // hamcrest
            assertThat(plant, hasProperty("genus", containsString("Quercus")))
        }
    }
}