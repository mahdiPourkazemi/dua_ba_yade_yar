package com.pourkazemi.mahdi.dua_bayadyar.ui.screen

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import com.pourkazemi.mahdi.dua_bayadyar.data.model.PrayerText
import com.pourkazemi.mahdi.dua_bayadyar.ui.component.DuaItem
import com.pourkazemi.mahdi.dua_bayadyar.ui.component.TranslationState
import com.pourkazemi.mahdi.dua_bayadyar.ui.component.rememberTranslationState
import kotlin.math.ceil

@Composable
fun DuaScreen(
    modifier: Modifier = Modifier,
    prayerText: PrayerText,
    maxWidth: Int,
    textStyle: TextStyle,
    translationTextStyle: TextStyle,
    translationState: TranslationState,
) {
    val textMeasurer = rememberTextMeasurer()

    // Measure text layouts
    val textLayout = remember(textStyle){
        textMeasurer.measure(
            text = prayerText.text,
            style = textStyle,
            constraints = Constraints.fixedWidth(maxWidth)
        )
    }

    val translationLayout = remember(translationTextStyle){
        textMeasurer.measure(
            text = prayerText.translation,
            style = translationTextStyle,
            constraints = Constraints.fixedWidth(maxWidth)
        )
    }

    // Calculate chunks
    val chunks = remember(textLayout,translationLayout){
        calculateChunks(
            textLayout = textLayout,
            translationLayout = translationLayout,
            linesPerChunk = 2,
            translationLinesPerChunk = 2,
            parentId = prayerText.id
        )
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(
            top=16.dp,
            bottom = 32.dp,
            start = 16.dp,
            end = 16.dp
        )
    ) {
        itemsIndexed(chunks) { index,TextChunk ->
            DuaItem(
                text = TextChunk.text,
                traText = TextChunk.translation,
                textStyle = textStyle,
                traTextStyle = translationTextStyle,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                translationState= translationState,
                itemId =  "${TextChunk.parentId}${TextChunk.id}".toInt()
            )
        }
    }
}
@VisibleForTesting
data class TextChunk(
    val parentId: Int, // شناسه والد (PrayerText)
    val id: Int,       // شناسه یکتا برای TextChunk
    val text: String,
    val translation: String
)

@VisibleForTesting
internal fun calculateChunks(
    textLayout: TextLayoutResult,
    translationLayout: TextLayoutResult,
    linesPerChunk: Int = 2,
    translationLinesPerChunk: Int = 2,
    parentId:Int
): List<TextChunk> {
    val textLineCount = textLayout.lineCount
    val translationLineCount = translationLayout.lineCount

    val chunks = mutableListOf<TextChunk>()
    val chunkCount = ceil(textLineCount.toFloat() / linesPerChunk).toInt()

    for (i in 0 until chunkCount) {
        val textStartLine = i * linesPerChunk
        val textEndLine = minOf(textStartLine + linesPerChunk, textLineCount)

        val translationStartLine = i * translationLinesPerChunk
        val translationEndLine = minOf(translationStartLine + translationLinesPerChunk, translationLineCount)

        val chunkText = extractTextByLines(textLayout, textStartLine, textEndLine)

        val chunkTranslation = if (isArabicNotPersian(chunkText)) {
            extractTextByLines(translationLayout, translationStartLine, translationEndLine)
        } else {
            ""
        }

        chunks.add(
            TextChunk(
                parentId = parentId,
                id = i,
                text = chunkText,
                translation = chunkTranslation
            )
        )
    }

    return chunks
}

@VisibleForTesting
private fun extractTextByLines(
    layoutResult: TextLayoutResult,
    startLine: Int,
    endLine: Int
): String {
    if (startLine >= layoutResult.lineCount) return ""
    val actualEndLine = minOf(endLine, layoutResult.lineCount)
    val startOffset = layoutResult.getLineStart(startLine)
    val endOffset = layoutResult.getLineEnd(actualEndLine - 1)
    return layoutResult.layoutInput.text.text.substring(startOffset, endOffset)
}

@VisibleForTesting
private fun isArabicNotPersian(text: String): Boolean {
    val arabicDiacritics = Regex("""[\u064B-\u0652]""")

    return arabicDiacritics.containsMatchIn(text)
}

@Preview(showBackground = true)
@Composable
fun DuaScreenPreview() {
    MaterialTheme {
        val samplePrayerText = PrayerText(
            id = 1,
            prayerid = 1,
            text = "اللّهُمَّ صَلِّ عَلَى مُحَمَّدٍ وَآلِ مُحَمَّدٍ",
            translation = "خدایا، بر محمد و آل محمد درود بفرست."
        )
        val textStyle = MaterialTheme.typography.bodyLarge
        val translationTextStyle = MaterialTheme.typography.bodyMedium
        val translationState = rememberTranslationState()

        DuaScreen(
            prayerText = samplePrayerText,
            maxWidth = 800,
            textStyle = textStyle,
            translationTextStyle = translationTextStyle,
            translationState = translationState
        )
    }
}

