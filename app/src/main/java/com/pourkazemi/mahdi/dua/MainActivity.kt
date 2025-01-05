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
import com.pourkazemi.mahdi.dua.data.model.Prayers
import com.pourkazemi.mahdi.dua.ui.screen.TopicListScreen
import com.pourkazemi.mahdi.dua.ui.theme.DuaTheme
import com.pourkazemi.mahdi.dua.viewModelAndUtils.MyApplication
import com.pourkazemi.mahdi.dua.viewModelAndUtils.PrayersViewModel
import com.pourkazemi.mahdi.dua.viewModelAndUtils.PrayersViewModelFactory

class MainActivity : ComponentActivity() {
    //#2 private lateinit var prayersViewModel: PrayersViewModel
    private val prayersViewModel: PrayersViewModel by viewModels {
        PrayersViewModelFactory((application as MyApplication).container.prayerRepository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//#1
       // val database = AppDatabaseImp.getDatabase(this).also { Log.d("Debug", "Database initialized") }
        //val repository = PrayersRepository(database.prayersDao(), database.prayerTextDao()).also { Log.d("Debug", "Repository initialized") }
        //val factory = PrayersViewModelFactory(repository).also { Log.d("Debug", "ViewModelFactory initialized") }
//#2
        //prayersViewModel = ViewModelProvider(this, factory).get(PrayersViewModel::class.java)

        setContent {
            DuaTheme {
                //val prayerText by prayersViewModel.getPrayerWithTextsList(2).collectAsState(initial = emptyList<PrayerWithText>())
                //val prayerText by prayersViewModel.prayerTexts.collectAsState()

                val prayers =prayersViewModel.allPrayersState.collectAsState()
                TopicListScreen(prayers= prayers.value)  {
                    Toast.makeText(this, "Hello, Toast! $it", Toast.LENGTH_SHORT).show()
                }
                // جمع‌آوری وضعیت UI از ViewModel
                /*val uiState by prayersViewModel.prayerUiState.collectAsState()

                // UI بر اساس وضعیت
                when (uiState.PrayerUiState) {
                    is Loading -> {
                        SplashScreen() // اسپلش اسکرین برای حالت Loading
                    }
                    is Success -> {
                        val prayerWithText = (uiState as PrayerUiState.Success).prayerWithText
                        prayerWithText.texts.getOrNull(0)?.let { DuaScreen(it) }
                    }
                    is Error -> {
                        Text("An error occurred. Please try again.") // پیام خطا
                    }

                }*/


                /*val prayers by prayersViewModel.allPrayers.observeAsState(emptyList())
                Log.d("Debug", "Prayers loaded: ${prayers.size}")
                if(prayers.isEmpty()){
                    MyApp(Modifier,prayers)
                }else{
                    //TestText(prayers)
                    PrayerListScreen(prayers)
                }*/

                /*prayersViewModel.insertPrayer(
                    prayers(id = 100, name = "test", description = "test")
                )*/
            }
        }
    }
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
fun TestText(prayers: List<Prayers>) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Text(modifier = Modifier.fillMaxWidth().padding(innerPadding), text = prayers.toString())
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