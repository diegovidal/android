package br.com.vp.plantplacespackt.service

import br.com.vp.plantplacespackt.dao.IPlantDAO
import br.com.vp.plantplacespackt.dao.PlantDAO
import br.com.vp.plantplacespackt.dao.PlantDAOStub
import br.com.vp.plantplacespackt.dao.PlantJsonDao
import br.com.vp.plantplacespackt.dto.PlantDTO

/**
 * @author diegovidal on 28/08/2018.
 */
class PlantService: IPlantService {

    private val plantDAO: IPlantDAO = PlantDAO()

    override fun fetchPlants(filter: String): List<PlantDTO> {

        return plantDAO.fetchPlants(filter)
    }
}