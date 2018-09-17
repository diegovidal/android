package br.com.vp.plantplacespackt.dao

import br.com.vp.plantplacespackt.NetworkDAO
import br.com.vp.plantplacespackt.dto.PlantDTO

/**
 * @author diegovidal on 28/08/2018.
 */
class PlantJsonDao: IPlantDAO {

    override var networkDAO: NetworkDAO? = null

    override fun fetchPlants(filter: String): List<PlantDTO> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}