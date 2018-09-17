package br.com.vp.plantplacespackt.dto

/**
 * @author diegovidal on 28/08/2018.
 */

class FlowerDTO(guid: Int,
                genus: String,
                species: String,
                cultivar: String,
                common: String,
                type: String)
    : PlantDTO(guid, genus, species, cultivar, common, type) {

    override fun toString(): String {

        return "Flower: ${super.toString()}"
    }
}