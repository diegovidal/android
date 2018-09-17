package br.com.vp.plantplacespackt

import br.com.vp.plantplacespackt.dao.BDDTestPlantMockitoDAO
import org.junit.runner.RunWith
import org.junit.runners.Suite

/**
 * @author diegovidal on 06/09/2018.
 */

@RunWith(Suite::class)
@Suite.SuiteClasses(
        BDDTestPlantMockitoDAO::class,
        TestNetworkDAO::class
)
class DAOTestSuite {
}