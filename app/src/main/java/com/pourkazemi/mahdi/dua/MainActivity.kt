package com.pourkazemi.mahdi.dua

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.pourkazemi.mahdi.dua.data.AppDatabaseImp
import com.pourkazemi.mahdi.dua.data.model.Prayers
import com.pourkazemi.mahdi.dua.data.repository.PrayersRepository
import com.pourkazemi.mahdi.dua.ui.component.TextItem
import com.pourkazemi.mahdi.dua.ui.screen.OnboardingScreen
import com.pourkazemi.mahdi.dua.ui.theme.Dua_velayeTheme

class MainActivity : ComponentActivity() {
    private lateinit var prayersViewModel: PrayersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = AppDatabaseImp.getDatabase(this).also { Log.d("Debug", "Database initialized") }
        val repository = PrayersRepository(database.prayersDao(), database.prayerTextDao()).also { Log.d("Debug", "Repository initialized") }
        val factory = PrayersViewModelFactory(repository).also { Log.d("Debug", "ViewModelFactory initialized") }

        prayersViewModel = ViewModelProvider(this, factory).get(PrayersViewModel::class.java)

        setContent {
            Dua_velayeTheme {
                val prayers by prayersViewModel.allPrayers.observeAsState(emptyList())
                Log.d("Debug", "Prayers loaded: ${prayers.size}")
                if(prayers.isEmpty()){
                    MyApp(Modifier,prayers)
                }else{
                    //TestText(prayers)
                    PrayerListScreen(prayers)
                }
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
                TextItem(
                    text = prayer.name,
                    traText = prayer.description
                ){

                }
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
            OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
        } else {
            PrayerListScreen(prayers)
        }
    }
}