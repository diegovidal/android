package com.dvidal.torneioscontinentais.data

import com.dvidal.torneioscontinentais.R
import com.dvidal.torneioscontinentais.domain.Game
import com.dvidal.torneioscontinentais.domain.Team


class MockData {
    companion object {
        fun getUefaTimes() = listOf<Game>(
            Game(
                Team("Juventus", R.drawable.es_juventus, R.drawable.ic_italia, 2),
                Team("Tottenham", R.drawable.es_tottenham, R.drawable.ic_inglaterra, 0),
                "Juventus Stadium",
                "13/02/2018",
                "17:45"
            ),
            Game(
                Team("Basel", R.drawable.es_basel, R.drawable.ic_suica, 0),
                Team("Manchester City", R.drawable.es_manchester_city, R.drawable.ic_inglaterra, 0),
                "St. Jakob-Park",
                "13/02/2018",
                "17:45"
            ),
            Game(
                Team("Porto", R.drawable.es_porto, R.drawable.ic_portugal, 2),
                Team("Liverpool", R.drawable.es_liverpool, R.drawable.ic_inglaterra, 5),
                "Estádio do Dragão",
                "14/02/2018",
                "17:45"
            ),
            Game(
                Team("Real Madrid", R.drawable.es_real_madrid, R.drawable.ic_espanha, 12),
                Team("Paris Saint Germain", R.drawable.es_psg, R.drawable.ic_franca, 0),
                "Santiago Bernabéu",
                "14/02/2018",
                "17:45"
            ),
            Game(
                Team("Bayern de Munique", R.drawable.es_bayern_munchen, R.drawable.ic_alemanha, 5),
                Team("Besiktas", R.drawable.es_besiktas, R.drawable.ic_turquia, 0),
                "Allianz Arena",
                "20/02/2018",
                "16:45"
            ),
            Game(
                Team("Chelsea", R.drawable.es_chelsea, R.drawable.ic_inglaterra, 1),
                Team("Barcelona", R.drawable.es_barcelona, R.drawable.ic_espanha, 5),
                "Stamford Bridge",
                "20/02/2018",
                "16:45"
            ),
            Game(
                Team("Sevilla", R.drawable.es_sevilla, R.drawable.ic_espanha, 0),
                Team("Manchester United", R.drawable.es_manchester_united, R.drawable.ic_inglaterra, 3),
                "Ramón Sánchez Pizjuán",
                "21/02/2018",
                "16:45"
            ),
            Game(
                Team("Shakhtar Donetsk", R.drawable.es_shakhtar_donetsk, R.drawable.ic_ucrania, 0),
                Team("Roma", R.drawable.es_roma, R.drawable.ic_italia, 0),
                "Arena Lviv",
                "21/02/2018",
                "16:45"
            )
        )

        fun getLibertadoresTimes() = listOf<Game>(
            Game(
                    Team("Godoy Cruz", R.drawable.es_godoy_cruz, R.drawable.ic_argentina, 0),
                    Team("Grêmio", R.drawable.es_gremio, R.drawable.ic_brasil, 3),
                    "Malvinas Argentinas",
                    "04/07/2017",
                    "21:45"
            ),
            Game(
                    Team("Guaraní", R.drawable.es_guarani, R.drawable.ic_paraguai, 0),
                    Team("River Plate", R.drawable.es_river_plate, R.drawable.ic_argentina, 3),
                    "Rogelio Livieres",
                    "04/07/2017",
                    "21:45"
            ),
            Game(
                    Team("Atlético-PR", R.drawable.es_atletico_paranaense, R.drawable.ic_brasil, 0),
                    Team("Santos", R.drawable.es_santos, R.drawable.ic_brasil, 3),
                    "Arena da Baixada",
                    "05/07/2017",
                    "21:45"
            ),
            Game(
                    Team("Jorge Wilstermann", R.drawable.es_jorge_wilstermann, R.drawable.ic_bolivia, 0),
                    Team("Atlético-MG", R.drawable.es_atletico_mineiro, R.drawable.ic_brasil, 1),
                    "Félix Capriles",
                    "05/07/2017",
                    "21:45"
            ),
            Game(
                    Team("Barcelona SC", R.drawable.es_barcelona_sc, R.drawable.ic_equador, 0),
                    Team("Palmeiras", R.drawable.es_palmeiras, R.drawable.ic_brasil, 1),
                    "Monumental Isidro Romero Carbo",
                    "05/07/2017",
                    "21:45"
            ),
            Game(
                    Team("The Strongest", R.drawable.es_the_strongest, R.drawable.ic_bolivia, 0),
                    Team("Lanús", R.drawable.es_lanus, R.drawable.ic_argentina, 0),
                    "Hernando Siles",
                    "06/07/2017",
                    "21:45"
            ),
            Game(
                    Team("Emelec", R.drawable.es_emelec, R.drawable.ic_equador, 0),
                    Team("San Lorenzo", R.drawable.es_san_lorenzo, R.drawable.ic_argentina, 1),
                    "George Capwell",
                    "06/07/2017",
                    "21:45"
            ),
            Game(
                    Team("Nacional", R.drawable.es_nacional, R.drawable.ic_uruguai, 3),
                    Team("Botafogo", R.drawable.es_botafogo, R.drawable.ic_brasil, 0),
                    "Gran Parque Central",
                    "07/07/2017",
                    "21:45"
            )
        )

        fun getAfcTimes() = listOf<Game>(
                Game(
                        Team("Al-Hilal", R.drawable.es_al_hilal, R.drawable.ic_arabia_saudita, 2),
                        Team("Esteghlal Khuzestan", R.drawable.es_esteghlal_khuzestan, R.drawable.ic_ira, 0),
                        "King Fahd Stadium",
                        "23/05/2017",
                        "20:15"
                ),
                Game(
                        Team("Esteghlal F.C.", R.drawable.es_esteghlal, R.drawable.ic_ira, 1),
                        Team("Al-Ain", R.drawable.es_al_ain, R.drawable.ic_emirados_arabes_unidos, 1),
                        "Azadi",
                        "22/05/2017",
                        "20:15"
                ),
                Game(
                        Team("Al Ahli", R.drawable.es_al_ahli, R.drawable.ic_emirados_arabes_unidos, 0),
                        Team("Al-Ahli Dubai", R.drawable.es_al_ahli_dubai, R.drawable.ic_emirados_arabes_unidos, 0),
                        "Al Rashid",
                        "22/05/2017",
                        "20:15"
                ),
                Game(
                        Team("Al-Duhail", R.drawable.es_al_duhail, R.drawable.ic_qatar, 0),
                        Team("Persepolis", R.drawable.es_persepolis, R.drawable.ic_ira, 0),
                        "Abdullah bin Khalifa",
                        "23/05/2017",
                        "20:15"
                ),
                Game(
                        Team("Shanghai East Asia", R.drawable.es_shanghai_east_asia, R.drawable.ic_china, 0),
                        Team("Jiangsu Suning", R.drawable.es_jiangsu_suning, R.drawable.ic_china, 0),
                        "Estádio de Shanghai",
                        "24/05/2017",
                        "20:15"
                ),
                Game(
                        Team("Kashima Antlers", R.drawable.es_kashima_antlers, R.drawable.ic_japao, 0),
                        Team("Guangzhou Evergrande", R.drawable.es_guangzhou_evergrande, R.drawable.ic_china, 2),
                        "Kashima",
                        "23/05/2017",
                        "20:15"
                ),
                Game(
                        Team("Kawasaki", R.drawable.es_kawasaki, R.drawable.ic_japao, 0),
                        Team("Muangthong", R.drawable.es_muangthong, R.drawable.ic_tailandia, 0),
                        "Todoroki Athletics Stadium",
                        "23/05/2017",
                        "20:15"
                ),
                Game(
                        Team("Jeju", R.drawable.es_jeju, R.drawable.ic_corea_do_sul, 0),
                        Team("Urawa Reds", R.drawable.es_urawa_reds, R.drawable.ic_japao, 1),
                        "Jeju World Cup Stadium",
                        "24/05/2017",
                        "20:15"
                )
        )
    }
}