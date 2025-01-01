package com.pourkazemi.mahdi.dua.ui.screen

import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Constraints

 object SplitTextHelper {
    data class TextPair(
        val original: String,
        val translation: String
    )
    fun getSplitTexts(
        originalText: String,
        translatedText: String,
        textMeasurer: TextMeasurer,
        maxWidth: Int,
        textStyle: TextStyle,
    ): List<TextPair> {
        val splitOriginal = measureAndSplitTextWithSpaces(
            originalText, textMeasurer, maxWidth, textStyle)
        val splitTranslated = measureAndSplitTextWithSpaces(
            translatedText, textMeasurer, maxWidth, textStyle)
        return createTextPairs(splitOriginal, splitTranslated)
    }

     private fun measureAndSplitTextWithSpaces(
         text: String,
         textMeasurer: TextMeasurer,
         maxWidth: Int,
         textStyle: TextStyle,

         ): List<String> {
         val chunks = mutableListOf<String>()
         val words = text.split(" ") // کلمات را با حفظ فاصله‌ها جدا می‌کنیم
         val currentChunk = StringBuilder()
         val currentLine = StringBuilder()

         for ((index, word) in words.withIndex()) {
             val testLine = if (currentLine.isEmpty()) word else "${currentLine} $word"

             val textLayoutResult = textMeasurer.measure(
                 text = testLine,
                 style = textStyle,
                 constraints = Constraints(maxWidth = maxWidth)
             )

             if (textLayoutResult.lineCount > 2) {
                 // اگر کلمه جدید باعث اضافه شدن خط شود
                 chunks.add(currentChunk.toString().trimEnd())
                 currentChunk.clear()
                 currentChunk.append(word).append(" ")
                 currentLine.clear()
                 currentLine.append(word)
             } else {
                 // کلمه فعلی را به خط و قطعه اضافه کن
                 currentLine.append(" ").append(word)
                 currentChunk.append(word)
                 if (index < words.lastIndex) currentChunk.append(" ")
             }
         }

         // اضافه کردن آخرین قطعه
         if (currentChunk.isNotEmpty()) {
             chunks.add(currentChunk.toString().trimEnd())
         }

         return chunks
     }

    private fun createTextPairs(
        originalLines: List<String>,
        translatedLines: List<String>
    ): List<TextPair> {
        val maxSize = maxOf(originalLines.size, translatedLines.size)
        return List(maxSize) { index ->
            TextPair(
                original = originalLines.getOrNull(index) ?: " ",
                translation = translatedLines.getOrNull(index) ?: " "
            )
        }
    }
}