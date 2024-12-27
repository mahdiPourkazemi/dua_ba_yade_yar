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
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pourkazemi.mahdi.dua.ui.component.TextItem
import com.pourkazemi.mahdi.dua.ui.preview.FullPreview
import com.pourkazemi.mahdi.dua.ui.preview.TextPreviewProvider

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

@FullPreview
@Composable
fun ArabicTextDisplayPreview(
   @PreviewParameter(
       TextPreviewProvider::class) text: String,
) {

    ArabicTextDisplay(
        text = text,
        maxLinesPerSegment = 3
    )
}
