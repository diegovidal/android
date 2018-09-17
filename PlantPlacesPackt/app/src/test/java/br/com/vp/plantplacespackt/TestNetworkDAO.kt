package br.com.vp.plantplacespackt

import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * @author diegovidal on 29/08/2018.
 */
class TestNetworkDAO {

    private lateinit var networkDAO: NetworkDAO

    @Before
    fun setup() {
        networkDAO = NetworkDAO()
    }

    @Test
    fun fetchShouldSucceedWhenGivenValidURI() {

        val result = networkDAO.fetch("http://plantplaces.com/perl/mobile/viewplantsjson.pl?Combined_Name=akjdf;lajksdf")
                .replace("\n","")
        println(result)
        assertEquals("{\"plants\":[]}-1", result)
    }
}