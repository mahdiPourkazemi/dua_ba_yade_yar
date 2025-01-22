package com.pourkazemi.mahdi.dua.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pourkazemi.mahdi.dua.DuaScreenData
import com.pourkazemi.mahdi.dua.R
import com.pourkazemi.mahdi.dua.data.model.PrayerText
import com.pourkazemi.mahdi.dua.data.model.Prayers
import com.pourkazemi.mahdi.dua.ui.component.TranslationState
import com.pourkazemi.mahdi.dua.ui.component.rememberTranslationState
import com.pourkazemi.mahdi.dua.ui.theme.MyTypography
import com.pourkazemi.mahdi.dua.viewModelAndUtils.PrayersViewModel

@Composable
fun PrayerListScreen(
    prayers: List<Prayers>,
    fontSize:Int,
    onItemClick: (Prayers) -> Unit
) {
    Scaffold(
        topBar = {
            Surface(
                modifier = Modifier
                    .heightIn(56.dp)
                    .fillMaxWidth(),
                shadowElevation = 4.dp,
                color = MaterialTheme.colorScheme.primary
            ) {
                Image(
                    painter = adaptiveIconPainterResource(id = R.drawable.ic_action_name),
                    contentDescription = "name of app",
                    modifier = Modifier.size(48.dp),
                    contentScale = ContentScale.FillHeight,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary)
                )
            }
        },
        content = { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                TopicListScreen(
                    prayers = prayers,
                    fontSize =fontSize,
                    onItemClick = onItemClick
                )
            }
        }
    )
}

@Composable
fun PagerScreenWithScaffold(
    prayerList:List<PrayerText>,
    duaScreenData: DuaScreenData,
    translationState: TranslationState ,//= rememberTranslationState(),
    initialTextStyle: TextStyle ,//= MyTypography.bodyMedium,
    currentTextStyle: (TextStyle) ->Unit,
) {
    /*val translationState = rememberTranslationState()
    var currentTextStyle by remember {
        mutableStateOf(TextStyle(fontSize = 18.sp))
    }*/
    val translationSize=(initialTextStyle.fontSize.value - (initialTextStyle.fontSize.value / 4))
    Scaffold(
        topBar = {
            CustomAppBar(
                title = duaScreenData.name,
                isSwitchChecked = translationState.globalTranslationEnabled,
                onSwitchChange = { enabled ->
                    translationState.toggleGlobalTranslation(enabled)
                },
                currentTextSize = initialTextStyle,
                onTextSizeChange = { newTextStyle ->
                    currentTextStyle( newTextStyle)
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            AutoAdvancePager(
                pageItems = prayerList,
                translationState = translationState,
                textStyle = initialTextStyle,
                translationTextStyle =MyTypography.bodySmall.copy(
                    fontSize = translationSize.toInt().sp
                )
            )
        }
    }
}

