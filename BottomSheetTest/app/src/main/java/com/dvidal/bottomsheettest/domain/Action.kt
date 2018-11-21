package com.dvidal.bottomsheettest.domain

import android.os.Parcel
import android.os.Parcelable


/**
 * @author diegovidal on 20/11/18.
 */
open class Action : Item {

    constructor(iconId: Int, label: String) : super(iconId, label) {}

    override fun describeContents(): Int {
        return 0
    }

    protected constructor(`in`: Parcel) : super(`in`)

    companion object {

        @JvmField
        val CREATOR: Parcelable.Creator<Action> = object : Parcelable.Creator<Action> {
            override fun createFromParcel(source: Parcel): Action {
                return Action(source)
            }

            override fun newArray(size: Int): Array<Action?> {
                return arrayOfNulls(size)
            }
        }
    }
}