package br.com.vp.plantplacespackt

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.vp.plantplacespackt.service.IPlantService
import br.com.vp.plantplacespackt.service.PlantService
import kotlinx.android.synthetic.main.activity_search_plants.*
import android.widget.ArrayAdapter
import br.com.vp.plantplacespackt.dto.PlantDTO
import android.os.AsyncTask


class SearchPlantsActivity : AppCompatActivity() {

    private val plantService: IPlantService = PlantService()

    var mPlants = arrayListOf<PlantDTO>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_plants)

        btnSearchPlants.setOnClickListener {
            searchPlants()
        }
    }

    private fun searchPlants() {

        val filter = actPlants.text.toString()
        PlantSearchTask().execute(filter)
    }

    @SuppressLint("StaticFieldLeak")
    inner class PlantSearchTask : AsyncTask<String, Void, MutableList<PlantDTO>>() {

        override fun onPostExecute(plants: MutableList<PlantDTO>) {
            val plantDTOArrayAdapter = ArrayAdapter(this@SearchPlantsActivity, android.R.layout.simple_list_item_1, plants)
            lstPlants.adapter = plantDTOArrayAdapter
            this@SearchPlantsActivity.mPlants = ArrayList(plants)
        }

        override fun doInBackground(vararg params: String): MutableList<PlantDTO>? {
            val plants = mutableListOf<PlantDTO>()
            try {
                plants.addAll(plantService.fetchPlants(params[0]))
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return plants
        }
    }
}
