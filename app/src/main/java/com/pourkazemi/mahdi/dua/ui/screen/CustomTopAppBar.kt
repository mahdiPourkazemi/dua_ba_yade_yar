package com.pourkazemi.mahdi.dua.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pourkazemi.mahdi.dua.DuaScreenData
import com.pourkazemi.mahdi.dua.data.model.Prayers
import com.pourkazemi.mahdi.dua.ui.component.rememberTranslationState
import com.pourkazemi.mahdi.dua.viewModelAndUtils.PrayersViewModel

@Composable
fun PrayerListScreen(prayers: List<Prayers>, onItemClick: (Prayers) -> Unit) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title =  "با یاد یار",
                showTranslation = false,
                isTranslationEnabled = false,
                onToggleChange = {}
            )
        },
        content = { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                TopicListScreen(
                    prayers = prayers,
                    onItemClick = onItemClick
                )
            }
        }
    )
}

@Composable
fun PagerScreenWithScaffold(
    prayersViewModel: PrayersViewModel,
    duaScreenData: DuaScreenData
) {
    val translationState = rememberTranslationState()

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = duaScreenData.name,
                showTranslation = true,
                isTranslationEnabled = translationState.globalTranslationEnabled,
                onToggleChange = { enabled ->
                    translationState.toggleGlobalTranslation(enabled)
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            AutoAdvancePager(
                pageItems = prayersViewModel.getPrayerTextsByPrayerId(duaScreenData.prayerId)
                    .collectAsStateWithLifecycle(initialValue = emptyList()).value,
                translationState = translationState
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    title: String,
    showTranslation: Boolean = false,
    isTranslationEnabled: Boolean,
    onToggleChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        actions = {
            if (showTranslation) {
                TranslationToggle(
                    isEnabled = isTranslationEnabled,
                    onToggleChange = onToggleChange
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        modifier = modifier
    )
}

@Composable
private fun TranslationToggle(
    isEnabled: Boolean,
    onToggleChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(end = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = "ترجمه",
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.labelMedium
        )

        Switch(
            checked = isEnabled,
            onCheckedChange = onToggleChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colorScheme.secondary,
                checkedTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                uncheckedThumbColor = MaterialTheme.colorScheme.onPrimary,
                uncheckedTrackColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f)
            ),
            modifier = Modifier.scale(0.8f)
        )
    }
}

