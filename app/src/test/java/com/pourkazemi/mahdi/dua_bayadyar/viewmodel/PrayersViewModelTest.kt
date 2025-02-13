import com.pourkazemi.mahdi.dua_bayadyar.data.repository.PrayerRepository
import com.pourkazemi.mahdi.dua_bayadyar.util.MainDispatcherRule
import com.pourkazemi.mahdi.dua_bayadyar.util.TestUtils
import com.pourkazemi.mahdi.dua_bayadyar.viewModelAndUtils.MyPreferencesImp
import com.pourkazemi.mahdi.dua_bayadyar.viewModelAndUtils.PrayersViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PrayersViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    
    private lateinit var repository: PrayerRepository
    private lateinit var preferences: MyPreferencesImp
    private lateinit var viewModel: PrayersViewModel

    @Before
    fun setup() {
        repository = mockk(relaxed = true)
        preferences = mockk(relaxed = true)
        viewModel = PrayersViewModel(repository, preferences)
    }
    
    @Test
    fun `when loading prayers, updates state correctly`() = runTest {
        // Given
        val prayers = listOf(TestUtils.createTestPrayer(1))
        coEvery { repository.allPrayers } returns flowOf(prayers)
        
        // When
        // loadAllPrayers is called in init block
        
        // Then
        assertEquals(prayers, viewModel.allPrayersState.value)
    }

    @Test
    fun `when loading preferences, updates data correctly`() = runTest {
        // Given
        val expectedValue = 20
        coEvery { preferences.readFromDataStore() } returns flowOf(expectedValue)
        
        // When
        viewModel.loadData()
        
        // Then
        assertEquals(expectedValue, viewModel.data.value)
    }
} 