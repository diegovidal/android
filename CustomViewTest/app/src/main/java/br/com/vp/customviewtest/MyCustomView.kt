package br.com.vp.customviewtest

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText

/**
 * @author diegovidal on 19/09/2018.
 */
class MyCustomView(context: Context, attributeSet: AttributeSet? = null)
    : EditText(context, attributeSet) {

    var seiLa = 0

    fun isEmail(): Boolean {

        val expReg = "^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]\$"

        val textTyped = text.toString()
        return textTyped.matches(Regex(expReg))
    }
}