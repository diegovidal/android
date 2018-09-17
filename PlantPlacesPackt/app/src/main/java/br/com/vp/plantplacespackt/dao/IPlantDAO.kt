package br.com.vp.plantplacespackt.dao

import br.com.vp.plantplacespackt.NetworkDAO
import br.com.vp.plantplacespackt.dto.PlantDTO

/**
 * A collection of methods to access plant data
 * @author diegovidal on 28/08/2018.
 */
interface IPlantDAO {

    var networkDAO: NetworkDAO?

    /**
     * Accept filter text, and return a collection of plants that contain that filter text.
     * @param filter the text we want to match in our returned list of plants.
     * @return a list of plants that contains the given filter text in either genus, species, cultivar, or common name.
     */
    fun fetchPlants(filter: String): List<PlantDTO>
}