package com.dvidal.torneioscontinentais.domain

import android.os.Parcel
import android.os.Parcelable


class Team(
        val nome: String?,
        val idEscudo: Int,
        val idBandeiraPais: Int,
        val qtdCampeonatos: Int) : Parcelable {

    constructor(source: Parcel) : this(
            source.readString(),
            source.readInt(),
            source.readInt(),
            source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(nome)
        writeInt(idEscudo)
        writeInt(idBandeiraPais)
        writeInt(qtdCampeonatos)
    }

    companion object {
        @JvmField
        val KEY = "key_time"

        @JvmField
        val CREATOR: Parcelable.Creator<Team> = object : Parcelable.Creator<Team> {
            override fun createFromParcel(source: Parcel): Team = Team(source)
            override fun newArray(size: Int): Array<Team?> = arrayOfNulls(size)
        }
    }
}