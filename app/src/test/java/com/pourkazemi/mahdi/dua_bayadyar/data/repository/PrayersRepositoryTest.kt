package com.pourkazemi.mahdi.dua_bayadyar.data.repository

import com.pourkazemi.mahdi.dua_bayadyar.data.cache.PrayerCache
import com.pourkazemi.mahdi.dua_bayadyar.data.dao.PrayerTextDao
import com.pourkazemi.mahdi.dua_bayadyar.data.dao.PrayersDao
import com.pourkazemi.mahdi.dua_bayadyar.util.MainDispatcherRule
import com.pourkazemi.mahdi.dua_bayadyar.util.TestUtils
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PrayersRepositoryTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var prayersDao: PrayersDao
    private lateinit var prayerTextDao: PrayerTextDao
    private lateinit var prayerCache: PrayerCache
    private lateinit var repository: PrayersRepository

    @Before
    fun setup() {
        prayersDao = mockk()
        prayerTextDao = mockk()
        prayerCache = mockk()
        repository = PrayersRepository(
            prayersDao = prayersDao,
            prayerTextDao = prayerTextDao,
            prayerCache = prayerCache,
            dispatcher = UnconfinedTestDispatcher()
        )
    }

    @Test
    fun `getAllPrayers returns flow of prayers`() = runTest {
        // Given
        val prayers = TestUtils.createTestPrayerList(3)
        coEvery { prayersDao.getAllPrayers() } returns flowOf(prayers)

        // When
        val result = repository.allPrayers

        // Then
        result.collect { 
            assertEquals(prayers, it)
        }
    }
} 