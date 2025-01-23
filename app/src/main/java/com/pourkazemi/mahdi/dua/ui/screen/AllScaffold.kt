package com.pourkazemi.mahdi.dua.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pourkazemi.mahdi.dua.DuaScreenData
import com.pourkazemi.mahdi.dua.R
import com.pourkazemi.mahdi.dua.data.model.PrayerText
import com.pourkazemi.mahdi.dua.data.model.Prayers
import com.pourkazemi.mahdi.dua.ui.component.TranslationState
import com.pourkazemi.mahdi.dua.ui.theme.MyTypography

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
    translationState: TranslationState ,
    initialTextStyle: TextStyle ,
    currentTextStyle: (TextStyle) ->Unit,
) {

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
            PagerScreen(
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

@Preview(showBackground = true, device = "spec:width=412dp,height=892dp")
@Composable
fun PrayerListScreenPreview() {
    val samplePrayers = listOf(
        Prayers(1,"صلاة الفجر", "صلاة الفجر هي أول صلوات اليوم"),
        Prayers(2,"صلاة الظهر", "صلاة الظهر تؤدى في منتصف النهار"),
        Prayers(3,"صلاة العصر", "صلاة العصر تؤدى بعد منتصف النهار")
    )

    PrayerListScreen(
        prayers = samplePrayers,
        fontSize = 16,
        onItemClick = {}
    )
}

@Preview(showBackground = true, device = "spec:width=412dp,height=892dp")
@Composable
fun PagerScreenWithScaffoldPreview() {
    val samplePrayerTexts = listOf(
        PrayerText(1,1,"بسم الله", "In the name of Allah"),
        PrayerText(2,1,"الحمد لله", "All praise to Allah")
    )

    val sampleDuaScreenData = DuaScreenData(1,"بسم الله")

    val translationState = remember { TranslationState() }

    val initialTextStyle = MyTypography.bodyLarge

    PagerScreenWithScaffold(
        prayerList = samplePrayerTexts,
        duaScreenData = sampleDuaScreenData,
        translationState = translationState,
        initialTextStyle = initialTextStyle,
        currentTextStyle = {}
    )
}