package br.com.vp.plantplacespackt.dao

import br.com.vp.plantplacespackt.NetworkDAO
import br.com.vp.plantplacespackt.dto.PlantDTO
import com.nhaarman.mockito_kotlin.whenever
import junit.framework.Assert
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * @author diegovidal on 06/09/2018.
 */
class BDDTestPlantMockitoDAO {

    private lateinit var plantDAO: IPlantDAO
    private lateinit var plants: List<PlantDTO>

    @Mock private lateinit var networkDAO: NetworkDAO

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testPlantDAO_fetchShouldReturnResultsForRedbud() {

        givenPlantDAOISInitialized()
        whenSearchForRedbud()
        thenVerifyAtLeastOneCercisCanadensis()
    }

    private fun givenPlantDAOISInitialized() {

        plantDAO = PlantDAO()

        // Here's where we mock our NetworkDAO
//        val networkDAO = mock(NetworkDAO::class.java)

        whenever(networkDAO.fetch("http://plantplaces.com/perl/mobile/viewplantsjson.pl?Combined_Name=sklujapouetllkjsda"))
                .thenReturn(gibberishJSON)

        whenever(networkDAO.fetch("http://plantplaces.com/perl/mobile/viewplantsjson.pl?Combined_Name=Quercus"))
                .thenReturn(quercusJSON)

        whenever(networkDAO.fetch("http://plantplaces.com/perl/mobile/viewplantsjson.pl?Combined_Name=Redbud"))
                .thenReturn(redbudJSON)


        plantDAO.networkDAO = networkDAO
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
        Assert.assertTrue(redbudFound)
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
        Assert.assertTrue(quercusRoburFound)

        // assume we do not have a match
        var quercusAlbaFound = false
        for (plant in plants){
            if (plant.genus.contains("Quercus", true) && plant.species.contains("alba", true) &&
                    plant.common.contains("Oak", true)){
                quercusAlbaFound = true
            }
        }

        // did we find a alba?
        Assert.assertTrue(quercusAlbaFound)
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

        MatcherAssert.assertThat(plants, Matchers.empty())

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

            MatcherAssert.assertThat(plant, Matchers.hasProperty("genus", Matchers.containsString("Quercus")))
        }
    }

    // ◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊

    val redbudJSON = "{\"plants\":[" +
            "{\"id\":\"83\",\"genus\":\"Cercis\",\"species\":\"canadensis\",\"cultivar\":\"\",\"common\":\"Eastern Redbud\"}," +
            "]}"

    val quercusJSON = "{\"plants\":[" +
            "{\"id\":\"286\",\"genus\":\"Quercus\",\"species\":\"alba\",\"cultivar\":\"\",\"common\":\"White Oak\"}," +
            "{\"id\":\"286\",\"genus\":\"Quercus\",\"species\":\"robur\",\"cultivar\":\"\",\"common\":\"English Oak\"}" +
            "]}"

    val gibberishJSON =
            "{\"plants\":[" +
                    "]" +
                    "}-1"
}