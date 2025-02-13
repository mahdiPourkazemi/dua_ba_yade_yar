package com.pourkazemi.mahdi.dua_bayadyar.data.cache

import com.pourkazemi.mahdi.dua_bayadyar.data.model.PrayerText
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class PrayerCache(private val maxSize: Int = 100) {
    private val mutex = Mutex()

    private val cache = object : LinkedHashMap<Int, PrayerText>(maxSize, 0.75f, true) {
        override fun removeEldestEntry(eldest: MutableMap.MutableEntry<Int, PrayerText>): Boolean {
            return size > maxSize
        }
    }

    suspend fun get(id: Int): PrayerText? = mutex.withLock {
        cache[id] // دسترسی باعث انتقال مقدار به انتهای لیست می‌شود (LRU)
    }

    suspend fun put(id: Int, prayer: PrayerText) = mutex.withLock {
        cache[id] = prayer
    }

    suspend fun clear() = mutex.withLock { cache.clear() }
}
