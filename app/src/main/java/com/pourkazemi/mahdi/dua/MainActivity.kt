package com.pourkazemi.mahdi.dua

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.pourkazemi.mahdi.dua.data.model.Prayers
import com.pourkazemi.mahdi.dua.ui.screen.AutoAdvancePager
import com.pourkazemi.mahdi.dua.ui.screen.TopicListScreen
import com.pourkazemi.mahdi.dua.ui.theme.DuaTheme
import com.pourkazemi.mahdi.dua.viewModelAndUtils.MyApplication
import com.pourkazemi.mahdi.dua.viewModelAndUtils.PrayerUiState
import com.pourkazemi.mahdi.dua.viewModelAndUtils.PrayersViewModel
import com.pourkazemi.mahdi.dua.viewModelAndUtils.PrayersViewModelFactory
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {

    private val prayersViewModel: PrayersViewModel by viewModels {
        PrayersViewModelFactory((application as MyApplication).container.prayerRepository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            DuaTheme {
                val prayers by prayersViewModel.allPrayersState.collectAsStateWithLifecycle()
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = MyScreen.TopicList.route
                ) {
                    // صفحه موضوعات (TopicList)
                    composable(MyScreen.TopicList.route) {
                        TopicListScreen(
                            prayers = prayers,
                            onItemClick = { prayerId ->
                                prayersViewModel.loadPrayer(prayerId)
                                navController.navigate(MyScreen.Dua.route)
                            }
                        )
                    }

                    // صفحه دعا (Dua)
                    composable(MyScreen.Dua.route) {
                        val prayerState by prayersViewModel.prayerUiState.collectAsStateWithLifecycle()

                        when (val state = prayerState) {
                            is PrayerUiState.Success -> {
                                AutoAdvancePager(pageItems = state.prayerWithText.texts)
                            }

                            is PrayerUiState.Error -> {
                                /*ErrorScreen(
                                    message = state.errorMessage,
                                    onRetry = { navController.popBackStack() }
                                )*/
                            }

                            PrayerUiState.Loading -> {
                                //LoadingScreen()
                            }
                        }
                    }
                }

            }
        }
    }
}


sealed class MyScreen(val route: String) {
    object TopicList : MyScreen("topic_list")
    object Dua : MyScreen("dua")
}

@Composable
fun PrayerListScreen(prayers: List<Prayers>) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(prayers.size) { index ->
                val prayer = prayers[index]
                /*TextItem(
                    text = prayer.name,
                    traText = prayer.description
                ){

                }*/
            }
        }
    }
}


@Composable
fun MyApp(modifier: Modifier = Modifier,
          prayers: List<Prayers> ) {

    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    Surface(modifier) {
        if (shouldShowOnboarding) {
            //OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
        } else {
            PrayerListScreen(prayers)
        }
    }
}