package com.dvidal.data.store

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * @author diegovidal on 30/09/2018.
 */

class ProjectsDataStoreFactoryTest {

    @Mock
    lateinit var cacheStore: ProjectsCacheDataStore

    @Mock
    lateinit var remoteStore: ProjectsRemoteDataStore

    lateinit var factory:  ProjectsDataStoreFactory

    @Before
    fun setup() {

        MockitoAnnotations.initMocks(this)
        factory = ProjectsDataStoreFactory(cacheStore, remoteStore)
    }

    @Test
    fun getDataStoreReturnsRemoteSourceWhenCacheExpired() {

//        assertEquals(remoteStore, utils.getDataStore(true, true))
        assert(factory.getDataStore(true, true) is ProjectsRemoteDataStore)
    }

    @Test
    fun getDataStoreReturnsRemoteSourceWhenNoCachedData() {
        assert(factory.getDataStore(false, false) is ProjectsRemoteDataStore)
    }

    @Test
    fun getCacheStoreRetrievesCacheSource() {
        assert(factory.getDataStore(true, false) is ProjectsCacheDataStore)
    }

    @Test
    fun getCacheDataStoreRetrievesCacheSource() {
        assert(factory.getCacheDataStore() is ProjectsCacheDataStore)
    }
}