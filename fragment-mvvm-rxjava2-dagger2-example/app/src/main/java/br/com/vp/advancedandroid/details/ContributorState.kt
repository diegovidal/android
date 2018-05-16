package br.com.vp.advancedandroid.details

import br.com.vp.advancedandroid.model.Contributor

/**
 * @author diegovidal on 02/05/2018.
 */
data class ContributorState(val loading: Boolean,
                            val errorRes: Int? = null) {

    fun isSuccess(): Boolean {
        return errorRes == null
    }
}
