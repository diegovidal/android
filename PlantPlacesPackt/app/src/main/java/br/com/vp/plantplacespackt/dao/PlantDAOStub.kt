package br.com.vp.plantplacespackt.dao

import br.com.vp.plantplacespackt.NetworkDAO
import br.com.vp.plantplacespackt.dto.FlowerDTO
import br.com.vp.plantplacespackt.dto.PlantDTO
import br.com.vp.plantplacespackt.dto.TreeDTO


/**
 * @author diegovidal on 28/08/2018.
 */

class PlantDAOStub: IPlantDAO {

    override var networkDAO: NetworkDAO? = null

    override fun fetchPlants(filter: String): List<PlantDTO> {

        val tree = TreeDTO(0,"Cercis", "canadensis", "", "Eastern Redbud",
                "tree", 30, "Yellowish")
        val flower = FlowerDTO(0,"Tropoleum", "spp", "", "Nasturtium",
                "flower")

        return arrayListOf(tree, flower)
    }
}