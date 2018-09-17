package br.com.vp.plantplacespackt.service

import br.com.vp.plantplacespackt.dto.PlantDTO

/**
 * Business logic for fetching and managing plants
 * @author diegovidal on 28/08/2018.
 */
interface IPlantService {

    /**
     * Return a series of plants that contain the specified filter text
     * @param filter text that sould be contained in the returned plants
     * @return a list of plants that match the specified search criteria
     */
    fun fetchPlants(filter: String): List<PlantDTO>

}