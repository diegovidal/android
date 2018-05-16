package br.com.vp.advancedandroid.details

/**
 * @author diegovidal on 02/05/2018.
 */
data class RepoDetailsState(val loading: Boolean,
                            val name: String? = null,
                            val description: String? = null,
                            val createdDate: String? = null,
                            val updatedDate: String? = null,
                            val errorRes: Int? = null) {

    fun isSuccess(): Boolean {
        return errorRes == null
    }
}