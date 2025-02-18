package com.pourkazemi.mahdi.dua_bayadyar

import org.junit.Test
import org.junit.Assert.*
import io.mockk.*
import androidx.compose.ui.text.TextLayoutResult
import com.pourkazemi.mahdi.dua_bayadyar.ui.screen.calculateChunks


class CalculateChunksTest {

    @Test
    fun `calculateChunks should return correct number of chunks`() {
        val textLayout = mockk<TextLayoutResult>()
        val translationLayout = mockk<TextLayoutResult>()

        every { textLayout.lineCount } returns 4
        every { translationLayout.lineCount } returns 4
        every { textLayout.getLineStart(any()) } returns 0
        every { textLayout.getLineEnd(any()) } returns 5
        every { translationLayout.getLineStart(any()) } returns 0
        every { translationLayout.getLineEnd(any()) } returns 5
        every { textLayout.layoutInput.text.text } returns "This is a test text"
        every { translationLayout.layoutInput.text.text } returns "این یک متن آزمایشی است"

        val chunks = calculateChunks(textLayout, translationLayout, 2, 2, 1)

        assertEquals(2, chunks.size)
    }

    @Test
    fun `calculateChunks should handle empty text`() {
        val textLayout = mockk<TextLayoutResult>()
        val translationLayout = mockk<TextLayoutResult>()

        every { textLayout.lineCount } returns 0
        every { translationLayout.lineCount } returns 0

        val chunks = calculateChunks(textLayout, translationLayout, 2, 2, 1)

        assertTrue(chunks.isEmpty())
    }

    @Test
    fun `calculateChunks should include translation only for Arabic text`() {
        val textLayout = mockk<TextLayoutResult>()
        val translationLayout = mockk<TextLayoutResult>()

        every { textLayout.lineCount } returns 2
        every { translationLayout.lineCount } returns 2
        every { textLayout.getLineStart(any()) } returns 0
        every { textLayout.getLineEnd(any()) } returns 5
        every { translationLayout.getLineStart(any()) } returns 0
        every { translationLayout.getLineEnd(any()) } returns 5
        every { textLayout.layoutInput.text.text } returns "السلام عليكم"
        every { translationLayout.layoutInput.text.text } returns "Hello there"

        val chunks = calculateChunks(textLayout, translationLayout, 2, 2, 1)

        assertEquals(1, chunks.size)
        assertEquals("السلام عليكم", chunks[0].text)
        assertEquals("Hello there", chunks[0].translation)
    }
}
