package br.com.vp.plantplacespackt.dao

import br.com.vp.plantplacespackt.dto.PlantDTO
import org.json.JSONObject
import br.com.vp.plantplacespackt.NetworkDAO



class PlantDAO : IPlantDAO {

    override var networkDAO: NetworkDAO? = NetworkDAO()

    override fun fetchPlants(filter: String): List<PlantDTO> {

        val allPlants = ArrayList<PlantDTO>()
        val request = networkDAO?.fetch("http://plantplaces.com/perl/mobile/viewplantsjson.pl?Combined_Name=$filter")

        // the entire JSON string is in root.
        val root = JSONObject(request)

        // now that we have root, let's get the first array, named plants
        val plants = root.getJSONArray("plants")

        for (i in 0 until plants.length()) {

            // this guy right here represents an individual plant.
            val jsonObject = plants.getJSONObject(i)
            val id = jsonObject.getInt("id")
            val genus = jsonObject.getString("genus")
            val species = jsonObject.getString("species")
            val cultivar = jsonObject.getString("cultivar")
            val common = jsonObject.getString("common")

            // now, let's put them into a PlantDTO.
            val plantDTO = PlantDTO(id, genus, species, cultivar, common, "")

            // add this plant to the collection.
            allPlants.add(plantDTO)
        }

        return allPlants
    }
}
