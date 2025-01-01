package com.pourkazemi.mahdi.dua.ui.screen

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import com.pourkazemi.mahdi.dua.data.model.PrayerText
import com.pourkazemi.mahdi.dua.ui.component.DuaItem
import com.pourkazemi.mahdi.dua.ui.preview.FullPreview
@Composable
fun DuaScreen(
    prayerText: PrayerText,
    textStyle: TextStyle = TextStyle.Default,
    modifier: Modifier = Modifier
) {
    val textMeasurer = rememberTextMeasurer()

    BoxWithConstraints(modifier = modifier
        .fillMaxSize(),
        propagateMinConstraints = true
    ) {
        val density = LocalDensity.current // گرفتن Density
        val maxWidth = with(density) {
            // محاسبه عرض مؤثر با تبدیل dp به px
            (maxWidth - 64.dp).toPx().toInt()
        }
        val splitTexts = remember(prayerText, maxWidth, textStyle) {
            SplitTextHelper.getSplitTexts(
                originalText = prayerText.text,
                translatedText = prayerText.translation,
                textMeasurer = textMeasurer,
                maxWidth = maxWidth ,
                textStyle = textStyle
            )
        }

        LazyColumn(modifier = Modifier
            .fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 16.dp),
        ) {
            items(splitTexts) { (originalLine, translatedLine) ->
                DuaItem(
                    text = originalLine,
                    traText = translatedLine,
                    style = textStyle,
                    //modifier = modifier.fillMaxWidth()
                )
            }
        }
    }
}



@FullPreview
@Composable
fun DuaScreenPreview() {
    val sampleText =
        "لا إِلهَ إِلّا أَنْتَ مُقَلِّبَ القُلُوبِ وَ الأَبْصارِ، لا إِلهَ إِلّا أَنْتَ خالِقَ الخَلْقِ بِلا حاجَةٍ فِيکَ إِلَيْهِمْ، لا إِلهَ إِلّا أَنْتَ مُبْدِئَ الخَلْقِ لا يَنْقُصُ مِنْ مُلْکِکَ شَيْ\u200Fءٌ. لا إِلهَ إِلّا أَنْتَ باعِثَ مَنْ فِي القُبُورِ، لا إِلهَ إِلّا أَنْتَ مُدَبِّرَ الأُمُورِ، لا إِلهَ إِلّا أَنْتَ دَيّانَ الدِّينِ وَ جَبّارَ الجَبابِرَةِ، لا إِلهَ إِلّا أَنْتَ مُجْرِيَ المآءِ فِي الصَّخْرَةِ الصَّمّآءِ، لا إِلهَ إلّا أَنْتَ مُجْرِيَ المآءِ فِي النَّباتِ، لا إِلهَ إِلّا أَنْتَ مُکَوِّنَ طَعْمِ الِّثمارِ، لا إِلهَ إِلّا أَنْتَ مُحْصِيَ عَدَدِ القَطَرِ وَ ما تَحْمِلُهُ السَّحابُ، لا إِلهَ إِلّا أَنْتَ مُحْصِيَ عَدَدِ ما تَجْرِي بِهِ الرِّياحُ فِي الهَوآءِ، لا إِلهَ إِلّا أَنْتَ مُحْصِيَ ما فِي البِحارِ مِنْ رَطْبٍ وَ يابِسٍ، لا إِلهَ إِلّا أَنْتَ مُحْصِيَ ما يَدِبُّ فِي ظُلُماتِ البِحارِ وَ فِي أَطْباقِ الثَّري، أَسْأَلُکَ بِاسْمِکَ الَّذِي سَمَّيْتَ بِهِ نَفْسَکَ، أَوِ اسْتَأْثَرْتَ بِهِ فِي عِلْمِ الغَيْبِ عِنْدَکَ وَ أَسْأَلُکَ بِکُلِّ اسْمٍ سَمَّاکَ بِهِ أَحَدٌ مِنْ خَلْقِکَ مِنْ نَبِيٍّ أَوْ صِدِّیقٍ أَوْ شَهِیدٍ أوْ أحَدٍ مِنْ مَلَائِکَتِکَ وَ أسْأَلُکَ بِاسْمِکَ الَّذِي إِذا دُعِيتَ بِهِ أَجَبْتَ وَ إِذَا سُئِلْتَ بِهِ أَعْطَيْتَ، وَ أَسْأَلُکَ بِحَقَّکَ عَلي مُحَمَّدٍ وَ أَهْلِ بَيْتِهِ صَلَواتُکَ عَلَيْهِمْ وَ بَرَکاتُکَ وَ بِحَقِّهِمُ الَّذِي أَوْجَبْتَهُ عَلي نَفْسِکَ وَ أَنَلْتَهُمْ بِهِ فَضْلَکَ أَنْ تُصَلِّيَ عَلي مُحَمَّدٍ عَبْدِکَ وَ رَسُولِکَ الدّاعِي إِلَيْکَ بِإِذْنِکَ وَ سِراجِکَ السّاطِعِ بَيْنَ عِبادِکَ فِي أَرْضِکَ وَ سَمآئِکَ وَ جَعَلْتَهُ رَحْمَةً لِلْعالَمِينَ وَ نُوراً اِسْتَضآءَ بِهِ المُؤْمِنُونَ فَبَشَّرَنا بِجَزِيلِ ثَوابِکَ وَ أَنْذَرَنا الأَلِيمَ مِنْ عَقَابِکَ، أَشْهَدُ أَنَّهُ قَدْ جآءَ بِالحَقِّ وَ صَدَّقَ المُرْسَلِينَ، وَ أَشْهَدُ أَنَّ الَّذِينَ کَذَّبُوهُ ذائِقُوا العَذابِ الأَلِيمِ، أَسْأَلُکَ يا اَللَّهُ يا اَللَّهُ يا اَللَّهُ، يا رَبّاهُ يا رَبّاهُ يا رَبّاهُ، يا سَيِّدِي يا سَيِّدِي يا سَيِّدِي، يا مَوْلايَ يا مَوْلايَ يا مَوْلايَ، أَسْأَلُکَ فِي هذِهِ الغَداةِ أَنْ تُصَلِّيَ عَلي مُحَمَّدٍ وَ آلِ مُحَمَّدٍ وَ أَنْ تَجْعَلَنِي مِنْ أَوْفَرِ عِبادِکَ وَ سآئِلِيکَ نَصِيباً وَ أَنْ تَمُنَّ عَلَيَّ بِفَکاکِ رَقَبَتِي مِنَ النّارِ، يا أَرْحَمَ الرّاحِمِينَ، وَ أَسْأَلُکَ بِجَمِيعِ ما سَأَلْتُکَ وَ ما لَمْ أَسْأَلْکَ مِنْ عَظِيمِ جَلالِکَ ما لَوْ عَلِمْتُهُ لَسَأَلْتُکَ بِهِ أَنْ تُصَلِّيَ عَلي مُحَمَّدٍ وَ أَهْلِ بَيْتِهِ وَ أَنْ تَأْذَنَ لِفَرَجِ مَنْ بِفَرَجِهِ فَرَجُ أَوْلِيآئِکَ وَ أَصْفِيآئِکَ مِنْ خَلْقِکَ وَ بِهِ تُبِيدُ الظّالِمِينَ وَ تُهْلِکُهُمْ، عَجِّلْ ذلِکَ يا رَبِّ العالَمِينَ وَ اعْطِنِي سُؤْلِي يا ذَاالجَلالِ وَالإِکْرامِ فِي جَمِيعِ ما سَأَلْتُکَ لِعاجِلِ الدُّنْيا وَ آجِلِ الآخِرَةِ، يا مَنْ هُوَ أَقْرَبُ إِلَيَّ مِنْ حَبْلِ الوَرِيدِ أَقِلْنِي عَثْرَتِي وَ أَقْلِبْنِي بِقَضآءِ حَوآئِجِي يا خالِقِي وَ يا رازِقِي وَ يا باعِثِي وَ يا مُحْيِي عِظامِي وَ هِيَ رَمِيمٌ، صَلِّ عَلي مُحَمَّدٍ وَآلِ مُحَمَّدٍ وَ اسْتَجِبْ لِي دُعآئِي يا أَرْحَمَ الرّاحِمِينَ ."

    val prayerText = PrayerText(
        id = 0,
        prayerid = 0,
        text = sampleText,
        translation = "There is no god but You, the Turner of hearts and sights..."
    )

    DuaScreen(
        prayerText = prayerText,
        textStyle = MaterialTheme.typography.bodyLarge.copy(
            textDirection = TextDirection.ContentOrRtl,
            color = MaterialTheme.colorScheme.onSurface
        )
    )
}