package com.pourkazemi.mahdi.dua

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.pourkazemi.mahdi.dua.ui.screen.PagerScreenWithScaffold
import com.pourkazemi.mahdi.dua.ui.screen.PrayerListScreen
import com.pourkazemi.mahdi.dua.ui.theme.DuaTheme
import com.pourkazemi.mahdi.dua.viewModelAndUtils.MyApplication
import com.pourkazemi.mahdi.dua.viewModelAndUtils.PrayersViewModel
import com.pourkazemi.mahdi.dua.viewModelAndUtils.PrayersViewModelFactory
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {

    private val prayersViewModel: PrayersViewModel by viewModels {
        PrayersViewModelFactory((application as MyApplication).container.prayerRepository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DuaTheme {
               
                val prayers by prayersViewModel.allPrayersState.collectAsStateWithLifecycle()
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = TopicListScreen
                ) {
                    // صفحه موضوعات (TopicList)
                    composable<TopicListScreen> {
                        PrayerListScreen(
                            prayers = prayers
                        ){ prayer ->
                        navController.navigate(DuaScreenData(prayer.id,prayer.name))
                    }
                    }
                    // صفحه دعا (Dua)
                    composable<DuaScreenData> {
                        val product : DuaScreenData = it.toRoute()
                        PagerScreenWithScaffold(
                            prayersViewModel = prayersViewModel,
                            duaScreenData = product
                        )

                    }
                }

            }
        }
    }
}

@Serializable
data class DuaScreenData(val prayerId: Int,val name:String)
@Serializable
private data object TopicListScreen
