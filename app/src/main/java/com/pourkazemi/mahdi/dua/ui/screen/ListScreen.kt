package com.pourkazemi.mahdi.dua.ui.screen

import android.graphics.Typeface
import android.text.Layout
import android.text.StaticLayout
import android.text.TextDirectionHeuristics
import android.text.TextPaint
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.TextUnit
import android.graphics.Paint
import android.graphics.text.LineBreaker
import android.os.Build
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pourkazemi.mahdi.dua.ui.component.TextItem

@Composable
fun ArabicTextDisplay(
    text: String,
    modifier: Modifier = Modifier,
    maxLinesPerSegment: Int = 3,
    fontSize: TextUnit = 16.sp,
    padding: PaddingValues = PaddingValues(16.dp)
) {
    val density = LocalDensity.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    val textPaint = remember(density) {
        TextPaint().apply {
            textSize = with(density) { fontSize.toPx() }
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
            isAntiAlias = true
            textAlign = Paint.Align.RIGHT
        }
    }

    val availableWidth = with(density) {
        (screenWidth - padding.calculateRightPadding(LayoutDirection.Ltr) * 5).toPx().toInt() }
    val textSegments = remember(text, availableWidth, maxLinesPerSegment, textPaint) {
        splitTextIntoSegments(text, availableWidth, maxLinesPerSegment, textPaint)
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(padding),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(textSegments) { segment ->
            TextItem(
                text = segment,
                traText = segment,
                clickOnItem = {},
            )
        }
    }
}

private fun splitTextIntoSegments(
    text: String,
    availableWidth: Int,
    maxLinesPerSegment: Int,
    textPaint: TextPaint
): List<String> {
    val words = text.split(" ")
    val segments = mutableListOf<String>()
    var currentSegment = StringBuilder()
    var wordIndex = 0

    while (wordIndex < words.size) {
        val nextWord = words[wordIndex]
        val testSegment = if (currentSegment.isEmpty()) nextWord
        else "${currentSegment.toString()} $nextWord"

        val measurement = createStaticLayout(
            text = testSegment,
            maxWidthPx = availableWidth,
            textPaint = textPaint,
            maxLinesPerSegment = maxLinesPerSegment
        )

        if (measurement.lineCount <= maxLinesPerSegment) {
            currentSegment.append(if (currentSegment.isEmpty()) nextWord else " $nextWord")
            wordIndex++
        } else {
            segments.add(currentSegment.toString())
            currentSegment = StringBuilder()
        }
    }

    if (currentSegment.isNotEmpty()) {
        segments.add(currentSegment.toString())
    }

    return segments
}

private fun createStaticLayout(
    text: String,
    maxWidthPx: Int,
    textPaint: TextPaint,
    maxLinesPerSegment: Int
): StaticLayout {
    val builder = StaticLayout.Builder
        .obtain(text, 0, text.length, textPaint, maxWidthPx)
        .setAlignment(Layout.Alignment.ALIGN_NORMAL)
        .setLineSpacing(0f, 1f)
        .setMaxLines(maxLinesPerSegment)
        .setIncludePad(false)
        .setTextDirection(TextDirectionHeuristics.RTL)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        builder
            .setBreakStrategy(LineBreaker.BREAK_STRATEGY_HIGH_QUALITY)
            .setHyphenationFrequency(Layout.HYPHENATION_FREQUENCY_NONE)
    }

    return builder.build()
}

@Preview
@Composable
fun ArabicTextDisplayPreview() {
    val longText = "لا إِلهَ إِلّا أَنْتَ مُقَلِّبَ القُلُوبِ وَ الأَبْصارِ، لا إِلهَ إِلّا أَنْتَ خالِقَ الخَلْقِ بِلا حاجَةٍ فِيکَ إِلَيْهِمْ، لا إِلهَ إِلّا أَنْتَ مُبْدِئَ الخَلْقِ لا يَنْقُصُ مِنْ مُلْکِکَ شَيْ\u200Fءٌ. لا إِلهَ إِلّا أَنْتَ باعِثَ مَنْ فِي القُبُورِ، لا إِلهَ إِلّا أَنْتَ مُدَبِّرَ الأُمُورِ، لا إِلهَ إِلّا أَنْتَ دَيّانَ الدِّينِ وَ جَبّارَ الجَبابِرَةِ، لا إِلهَ إِلّا أَنْتَ مُجْرِيَ المآءِ فِي الصَّخْرَةِ الصَّمّآءِ، لا إِلهَ إلّا أَنْتَ مُجْرِيَ المآءِ فِي النَّباتِ، لا إِلهَ إِلّا أَنْتَ مُکَوِّنَ طَعْمِ الِّثمارِ، لا إِلهَ إِلّا أَنْتَ مُحْصِيَ عَدَدِ القَطَرِ وَ ما تَحْمِلُهُ السَّحابُ، لا إِلهَ إِلّا أَنْتَ مُحْصِيَ عَدَدِ ما تَجْرِي بِهِ الرِّياحُ فِي الهَوآءِ، لا إِلهَ إِلّا أَنْتَ مُحْصِيَ ما فِي البِحارِ مِنْ رَطْبٍ وَ يابِسٍ، لا إِلهَ إِلّا أَنْتَ مُحْصِيَ ما يَدِبُّ فِي ظُلُماتِ البِحارِ وَ فِي أَطْباقِ الثَّري، أَسْأَلُکَ بِاسْمِکَ الَّذِي سَمَّيْتَ بِهِ نَفْسَکَ، أَوِ اسْتَأْثَرْتَ بِهِ فِي عِلْمِ الغَيْبِ عِنْدَکَ وَ أَسْأَلُکَ بِکُلِّ اسْمٍ سَمَّاکَ بِهِ أَحَدٌ مِنْ خَلْقِکَ مِنْ نَبِيٍّ أَوْ صِدِّیقٍ أَوْ شَهِیدٍ أوْ أحَدٍ مِنْ مَلَائِکَتِکَ وَ أسْأَلُکَ بِاسْمِکَ الَّذِي إِذا دُعِيتَ بِهِ أَجَبْتَ وَ إِذَا سُئِلْتَ بِهِ أَعْطَيْتَ، وَ أَسْأَلُکَ بِحَقَّکَ عَلي مُحَمَّدٍ وَ أَهْلِ بَيْتِهِ صَلَواتُکَ عَلَيْهِمْ وَ بَرَکاتُکَ وَ بِحَقِّهِمُ الَّذِي أَوْجَبْتَهُ عَلي نَفْسِکَ وَ أَنَلْتَهُمْ بِهِ فَضْلَکَ أَنْ تُصَلِّيَ عَلي مُحَمَّدٍ عَبْدِکَ وَ رَسُولِکَ الدّاعِي إِلَيْکَ بِإِذْنِکَ وَ سِراجِکَ السّاطِعِ بَيْنَ عِبادِکَ فِي أَرْضِکَ وَ سَمآئِکَ وَ جَعَلْتَهُ رَحْمَةً لِلْعالَمِينَ وَ نُوراً اِسْتَضآءَ بِهِ المُؤْمِنُونَ فَبَشَّرَنا بِجَزِيلِ ثَوابِکَ وَ أَنْذَرَنا الأَلِيمَ مِنْ عَقَابِکَ، أَشْهَدُ أَنَّهُ قَدْ جآءَ بِالحَقِّ وَ صَدَّقَ المُرْسَلِينَ، وَ أَشْهَدُ أَنَّ الَّذِينَ کَذَّبُوهُ ذائِقُوا العَذابِ الأَلِيمِ، أَسْأَلُکَ يا اَللَّهُ يا اَللَّهُ يا اَللَّهُ، يا رَبّاهُ يا رَبّاهُ يا رَبّاهُ، يا سَيِّدِي يا سَيِّدِي يا سَيِّدِي، يا مَوْلايَ يا مَوْلايَ يا مَوْلايَ، أَسْأَلُکَ فِي هذِهِ الغَداةِ أَنْ تُصَلِّيَ عَلي مُحَمَّدٍ وَ آلِ مُحَمَّدٍ وَ أَنْ تَجْعَلَنِي مِنْ أَوْفَرِ عِبادِکَ وَ سآئِلِيکَ نَصِيباً وَ أَنْ تَمُنَّ عَلَيَّ بِفَکاکِ رَقَبَتِي مِنَ النّارِ، يا أَرْحَمَ الرّاحِمِينَ، وَ أَسْأَلُکَ بِجَمِيعِ ما سَأَلْتُکَ وَ ما لَمْ أَسْأَلْکَ مِنْ عَظِيمِ جَلالِکَ ما لَوْ عَلِمْتُهُ لَسَأَلْتُکَ بِهِ أَنْ تُصَلِّيَ عَلي مُحَمَّدٍ وَ أَهْلِ بَيْتِهِ وَ أَنْ تَأْذَنَ لِفَرَجِ مَنْ بِفَرَجِهِ فَرَجُ أَوْلِيآئِکَ وَ أَصْفِيآئِکَ مِنْ خَلْقِکَ وَ بِهِ تُبِيدُ الظّالِمِينَ وَ تُهْلِکُهُمْ، عَجِّلْ ذلِکَ يا رَبِّ العالَمِينَ وَ اعْطِنِي سُؤْلِي يا ذَاالجَلالِ وَالإِکْرامِ فِي جَمِيعِ ما سَأَلْتُکَ لِعاجِلِ الدُّنْيا وَ آجِلِ الآخِرَةِ، يا مَنْ هُوَ أَقْرَبُ إِلَيَّ مِنْ حَبْلِ الوَرِيدِ أَقِلْنِي عَثْرَتِي وَ أَقْلِبْنِي بِقَضآءِ حَوآئِجِي يا خالِقِي وَ يا رازِقِي وَ يا باعِثِي وَ يا مُحْيِي عِظامِي وَ هِيَ رَمِيمٌ، صَلِّ عَلي مُحَمَّدٍ وَآلِ مُحَمَّدٍ وَ اسْتَجِبْ لِي دُعآئِي يا أَرْحَمَ الرّاحِمِينَ ."

    ArabicTextDisplay(
        text = longText,
        maxLinesPerSegment = 3
    )
}
