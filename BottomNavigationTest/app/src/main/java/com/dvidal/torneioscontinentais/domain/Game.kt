package com.dvidal.torneioscontinentais.domain


class Game(
        val teamCasa: Team,
        val teamVisitante: Team,
        val estadio: String,
        val data: String,
        val horario: String) {
}