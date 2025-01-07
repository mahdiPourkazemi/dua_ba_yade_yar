package com.pourkazemi.mahdi.dua.ui.testComponent

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import com.pourkazemi.mahdi.dua.ui.component.DuaItem
import com.pourkazemi.mahdi.dua.ui.preview.FullPreview
import com.pourkazemi.mahdi.dua.ui.theme.DuaTheme
import com.pourkazemi.mahdi.dua.ui.theme.MyTypography
import kotlinx.coroutines.delay
import java.lang.reflect.Type
import kotlin.math.ceil


//#Todo add prayer object to function and modify function and
//# Todo // clean code check by AI
@Composable
fun LazyTextLayout(
    modifier: Modifier = Modifier,
    text: String,
    textWidth: Int = 360,
    textStyle: TextStyle, //= TextStyle.Default,
    traTextStyle: TextStyle //= TextStyle.Default
) {
    val textMeasurer = rememberTextMeasurer()

    val layoutResult = textMeasurer.measure(
        text = text,
        style = textStyle,
        constraints = Constraints.fixedWidth(textWidth)//fitPrioritizingWidth( )//cons
    )

    val lineCount = layoutResult.lineCount
    val linesPerChunk = 2
    val chunks = if (lineCount > 0) ceil(lineCount.toFloat() / linesPerChunk).toInt() else 0

    LazyColumn(modifier = modifier
        .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp),
    ) {
        items(chunks) { chunkIndex ->
            val startLine = chunkIndex * linesPerChunk
            val endLine = minOf(startLine + linesPerChunk, lineCount)
            val chunkText = extractTextByLines(layoutResult, startLine, endLine)

            DuaItem(
                text = chunkText,
                textStyle  = textStyle,
                traTextStyle = traTextStyle,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            )
        }
    }
}


private fun extractTextByLines(
    layoutResult: TextLayoutResult,
    startLine: Int,
    endLine: Int
): String {
    val startOffset = layoutResult.getLineStart(startLine)
    val endOffset = layoutResult.getLineEnd(endLine - 1)

    return layoutResult.layoutInput.text.text.substring(startOffset, endOffset)
}

@FullPreview
@Composable
private fun T10Preview() {
    val density = LocalDensity.current // گرفتن Density
DuaTheme {
    BoxWithConstraints (Modifier
       .fillMaxSize(),
    ) {
        val max_Width = with(density) {
            // محاسبه عرض مؤثر با تبدیل dp به px
            (maxWidth - 64.dp).toPx().toInt()
        }
       val sampleText =
           "لا إِلهَ إِلّا أَنْتَ مُقَلِّبَ القُلُوبِ وَ الأَبْصارِ، لا إِلهَ إِلّا أَنْتَ خالِقَ الخَلْقِ بِلا حاجَةٍ فِيکَ إِلَيْهِمْ، لا إِلهَ إِلّا أَنْتَ مُبْدِئَ الخَلْقِ لا يَنْقُصُ مِنْ مُلْکِکَ شَي\u200Fءٌ. لا إِلهَ إِلّا أَنْتَ باعِثَ مَنْ فِي القُبُورِ، لا إِلهَ إِلّا أَنْتَ مُدَبِّرَ الأُمُورِ، لا إِلهَ إِلّا أَنْتَ دَيّانَ الدِّينِ وَ جَبّارَ الجَبابِرَةِ، لا إِلهَ إِلّا أَنْتَ مُجْرِيَ المآءِ فِي الصَّخْرَةِ الصَّمّآءِ، لا إِلهَ إلّا أَنْتَ مُجْرِيَ المآءِ فِي النَّباتِ، لا إِلهَ إِلّا أَنْتَ مُکَوِّنَ طَعْمِ الِّثمارِ، لا إِلهَ إِلّا أَنْتَ مُحْصِيَ عَدَدِ القَطَرِ وَ ما تَحْمِلُهُ السَّحابُ، لا إِلهَ إِلّا أَنْتَ مُحْصِيَ عَدَدِ ما تَجْرِي بِهِ الرِّياحُ فِي الهَوآءِ، لا إِلهَ إِلّا أَنْتَ مُحْصِيَ ما فِي البِحارِ مِنْ رَطْبٍ وَ يابِسٍ، لا إِلهَ إِلّا أَنْتَ مُحْصِيَ ما يَدِبُّ فِي ظُلُماتِ البِحارِ وَ فِي أَطْباقِ الثَّري، أَسْأَلُکَ بِاسْمِکَ الَّذِي سَمَّيْتَ بِهِ نَفْسَکَ، أَوِ اسْتَأْثَرْتَ بِهِ فِي عِلْمِ الغَيْبِ عِنْدَکَ وَ أَسْأَلُکَ بِکُلِّ اسْمٍ سَمَّاکَ بِهِ أَحَدٌ مِنْ خَلْقِکَ مِنْ نَبِيٍّ أَوْ صِدِّیقٍ أَوْ شَهِیدٍ أوْ أحَدٍ مِنْ مَلَائِکَتِکَ وَ أسْأَلُکَ بِاسْمِکَ الَّذِي إِذا دُعِيتَ بِهِ أَجَبْتَ وَ إِذَا سُئِلْتَ بِهِ أَعْطَيْتَ، وَ أَسْأَلُکَ بِحَقَّکَ عَلي مُحَمَّدٍ وَ أَهْلِ بَيْتِهِ صَلَواتُکَ عَلَيْهِمْ وَ بَرَکاتُکَ وَ بِحَقِّهِمُ الَّذِي أَوْجَبْتَهُ عَلي نَفْسِکَ وَ أَنَلْتَهُمْ بِهِ فَضْلَکَ أَنْ تُصَلِّيَ عَلي مُحَمَّدٍ عَبْدِکَ وَ رَسُولِکَ الدّاعِي إِلَيْکَ بِإِذْنِکَ وَ سِراجِکَ السّاطِعِ بَيْنَ عِبادِکَ فِي أَرْضِکَ وَ سَمآئِکَ وَ جَعَلْتَهُ رَحْمَةً لِلْعالَمِينَ وَ نُوراً اِسْتَضآءَ بِهِ المُؤْمِنُونَ فَبَشَّرَنا بِجَزِيلِ ثَوابِکَ وَ أَنْذَرَنا الأَلِيمَ مِنْ عَقَابِکَ، أَشْهَدُ أَنَّهُ قَدْ جآءَ بِالحَقِّ وَ صَدَّقَ المُرْسَلِينَ، وَ أَشْهَدُ أَنَّ الَّذِينَ کَذَّبُوهُ ذائِقُوا العَذابِ الأَلِيمِ، أَسْأَلُکَ يا اَللَّهُ يا اَللَّهُ يا اَللَّهُ، يا رَبّاهُ يا رَبّاهُ يا رَبّاهُ، يا سَيِّدِي يا سَيِّدِي يا سَيِّدِي، يا مَوْلايَ يا مَوْلايَ يا مَوْلايَ، أَسْأَلُکَ فِي هذِهِ الغَداةِ أَنْ تُصَلِّيَ عَلي مُحَمَّدٍ وَ آلِ مُحَمَّدٍ وَ أَنْ تَجْعَلَنِي مِنْ أَوْفَرِ عِبادِکَ وَ سآئِلِيکَ نَصِيباً وَ أَنْ تَمُنَّ عَلَيَّ بِفَکاکِ رَقَبَتِي مِنَ النّارِ، يا أَرْحَمَ الرّاحِمِينَ، وَ أَسْأَلُکَ بِجَمِيعِ ما سَأَلْتُکَ وَ ما لَمْ أَسْأَلْکَ مِنْ عَظِيمِ جَلالِکَ ما لَوْ عَلِمْتُهُ لَسَأَلْتُکَ بِهِ أَنْ تُصَلِّيَ عَلي مُحَمَّدٍ وَ أَهْلِ بَيْتِهِ وَ أَنْ تَأْذَنَ لِفَرَجِ مَنْ بِفَرَجِهِ فَرَجُ أَوْلِيآئِکَ وَ أَصْفِيآئِکَ مِنْ خَلْقِکَ وَ بِهِ تُبِيدُ الظّالِمِينَ وَ تُهْلِکُهُمْ، عَجِّلْ ذلِکَ يا رَبِّ العالَمِينَ وَ اعْطِنِي سُؤْلِي يا ذَاالجَلالِ وَالإِکْرامِ فِي جَمِيعِ ما سَأَلْتُکَ لِعاجِلِ الدُّنْيا وَ آجِلِ الآخِرَةِ، يا مَنْ هُوَ أَقْرَبُ إِلَيَّ مِنْ حَبْلِ الوَرِيدِ أَقِلْنِي عَثْرَتِي وَ أَقْلِبْنِي بِقَضآءِ حَوآئِجِي يا خالِقِي وَ يا رازِقِي وَ يا باعِثِي وَ يا مُحْيِي عِظامِي وَ هِيَ رَمِيمٌ، صَلِّ عَلي مُحَمَّدٍ وَآلِ مُحَمَّدٍ وَ اسْتَجِبْ لِي دُعآئِي يا أَرْحَمَ الرّاحِمِينَ .".repeat(2)

        LazyTextLayout(
            text = sampleText,
            textWidth = max_Width,
            textStyle = MyTypography.bodyMedium,
            traTextStyle = MyTypography.bodySmall
        )
    }
}

}