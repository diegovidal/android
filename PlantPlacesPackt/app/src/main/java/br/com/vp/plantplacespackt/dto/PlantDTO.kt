package br.com.vp.plantplacespackt.dto

/**
 * @author diegovidal on 28/08/2018.
 */
open class PlantDTO(val guid: Int,
                    val genus: String,
                    val species: String,
                    val cultivar: String,
                    val common: String,
                    val type: String) {

    override fun toString(): String{

        return "$genus $species $cultivar $common"
    }
}