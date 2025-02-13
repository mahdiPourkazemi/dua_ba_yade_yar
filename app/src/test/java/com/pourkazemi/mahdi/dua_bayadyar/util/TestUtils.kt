package com.pourkazemi.mahdi.dua_bayadyar.util

import com.pourkazemi.mahdi.dua_bayadyar.data.model.PrayerText
import com.pourkazemi.mahdi.dua_bayadyar.data.model.Prayers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@OptIn(ExperimentalCoroutinesApi::class)
class MainDispatcherRule(
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
) : TestWatcher() {
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}

object TestUtils {
    fun createTestPrayer(id: Int) = Prayers(
        id = id,
        name = "Test Prayer $id",
        description = "Test Description $id"
    )

    fun createTestPrayerText(id: Int, prayerId: Int) = PrayerText(
        id = id,
        prayerid = prayerId,
        text = "Test Text $id",
        translation = "Test Translation $id"
    )

    fun createTestPrayerList(count: Int) = List(count) { index ->
        createTestPrayer(index + 1)
    }

    fun createTestPrayerTextList(prayerId: Int, count: Int) = List(count) { index ->
        createTestPrayerText(index + 1, prayerId)
    }
} 