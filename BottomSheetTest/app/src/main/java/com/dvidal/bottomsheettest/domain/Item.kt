package com.dvidal.bottomsheettest.domain

import android.os.Parcel
import android.os.Parcelable


/**
 * @author diegovidal on 20/11/18.
 */
open class Item : Parcelable {

    private var iconId: Int = 0
    var label: String? = null
        private set

    constructor(iconId: Int, label: String) {
        this.iconId = iconId
        this.label = label
    }

    fun getIconId(): Int {
        return this.iconId
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(this.iconId)
        dest.writeString(this.label)
    }

    constructor(`in`: Parcel) {
        this.iconId = `in`.readInt()
        this.label = `in`.readString()
    }

    companion object {

        @JvmField
        val CREATOR: Parcelable.Creator<Item> = object : Parcelable.Creator<Item> {
            override fun createFromParcel(source: Parcel): Item {
                return Item(source)
            }

            override fun newArray(size: Int): Array<Item?> {
                return arrayOfNulls(size)
            }
        }
    }
}