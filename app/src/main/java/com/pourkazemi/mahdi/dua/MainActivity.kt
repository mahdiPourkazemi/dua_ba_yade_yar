package com.pourkazemi.mahdi.dua

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.pourkazemi.mahdi.dua.ui.component.rememberTranslationState
import com.pourkazemi.mahdi.dua.ui.screen.PagerScreenWithScaffold
import com.pourkazemi.mahdi.dua.ui.screen.PrayerListScreen
import com.pourkazemi.mahdi.dua.ui.theme.DuaTheme
import com.pourkazemi.mahdi.dua.viewModelAndUtils.MyApplication
import com.pourkazemi.mahdi.dua.viewModelAndUtils.PrayersViewModel
import com.pourkazemi.mahdi.dua.viewModelAndUtils.PrayersViewModelFactory
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {

    private val prayersViewModel: PrayersViewModel by viewModels {
        PrayersViewModelFactory((application as MyApplication).container.prayerRepository,
            (application as MyApplication).preference)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DuaTheme {
                val translationState = rememberTranslationState()
                val sizeOfFontShearedPreference by prayersViewModel.data.collectAsStateWithLifecycle()
                var currentTextStyle by remember(sizeOfFontShearedPreference) {
                    mutableStateOf(
                        TextStyle(fontSize = sizeOfFontShearedPreference.sp))
                }
                val prayers by prayersViewModel.allPrayersState.collectAsStateWithLifecycle()
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = TopicListScreen
                ) {
                    // صفحه موضوعات (TopicList)
                    composable<TopicListScreen> {
                        PrayerListScreen(
                            prayers = prayers,
                            fontSize = currentTextStyle.fontSize.value.toInt()      //#Todo fix this and using textStyle
                        ){ prayer ->
                        navController.navigate(DuaScreenData(prayer.id,prayer.name))
                    }
                    }
                    // صفحه دعا (Dua)
                    composable<DuaScreenData> {
                        val duaScreenData : DuaScreenData = it.toRoute()
                        val prayerTexts = prayersViewModel.getPrayerTextsByPrayerId(duaScreenData.prayerId)
                            .collectAsStateWithLifecycle(initialValue = emptyList()).value
                        PagerScreenWithScaffold(
                            prayerList = prayerTexts,
                            duaScreenData = duaScreenData,
                            translationState = translationState,
                            initialTextStyle = currentTextStyle,
                        ){
                            currentTextStyle=it
                            prayersViewModel.saveData(it.fontSize.value.toInt())
                        }

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
