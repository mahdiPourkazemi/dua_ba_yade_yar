package com.pourkazemi.mahdi.dua_bayadyar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.pourkazemi.mahdi.dua_bayadyar.ui.component.rememberTranslationState
import com.pourkazemi.mahdi.dua_bayadyar.ui.screen.PagerScreenWithScaffold
import com.pourkazemi.mahdi.dua_bayadyar.ui.screen.PrayerListScreen
import com.pourkazemi.mahdi.dua_bayadyar.ui.theme.DuaTheme
import com.pourkazemi.mahdi.dua_bayadyar.di.MyApplication
import com.pourkazemi.mahdi.dua_bayadyar.viewModelAndUtils.PrayersViewModel
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val prayersViewModel = viewModel<PrayersViewModel>(
                factory = MyApplication.appModule.myViewModelFactory
            )

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
                            fontSize = currentTextStyle.fontSize.value.toInt()//? fix this and using textStyle
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
                        ){ textStyle->
                            currentTextStyle = textStyle
                            prayersViewModel.saveData(textStyle.fontSize.value.toInt())
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
