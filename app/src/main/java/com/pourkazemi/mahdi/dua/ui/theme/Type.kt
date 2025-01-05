package com.pourkazemi.mahdi.dua.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.style.TextAlign
import com.pourkazemi.mahdi.dua.R


// تعریف فونت‌های وزیر
val VazirmatnFontFamily = FontFamily(
    Font(R.font.nabi, FontWeight.Normal),
    Font(R.font.nabi, FontWeight.Medium),
    Font(R.font.nabi, FontWeight.Bold),
    Font(R.font.nabi, FontWeight.Light)
)

// Typography طبق متریال دیزاین 3
val Typography = Typography(
    // Body Large - برای متن‌های اصلی
    bodyLarge = TextStyle(
        fontFamily = VazirmatnFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 33.sp,
        letterSpacing = 4.sp,
        textDirection = TextDirection.Rtl,
        textAlign = TextAlign.Right
    ),

    // Body Medium
    bodyMedium = TextStyle(
        fontFamily = VazirmatnFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 30.sp,
        letterSpacing = 5.sp,
        textDirection = TextDirection.Rtl,
        textAlign = TextAlign.Right
    ),

    // Body Small
    bodySmall = TextStyle(
        fontFamily = VazirmatnFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 24.sp,
        letterSpacing = 6.sp,
        textDirection = TextDirection.Rtl,
        textAlign = TextAlign.Right
    ),

    // Label Large - برای برچسب‌ها
    labelLarge = TextStyle(
        fontFamily = VazirmatnFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 2.sp,
        textDirection = TextDirection.Rtl,
        textAlign = TextAlign.Right
    ),

    // Label Medium
    labelMedium = TextStyle(
        fontFamily = VazirmatnFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 2.sp,
        textDirection = TextDirection.Rtl,
        textAlign = TextAlign.Right
    ),

    // Label Small
    labelSmall = TextStyle(
        fontFamily = VazirmatnFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 4.sp,
        textDirection = TextDirection.Rtl,
        textAlign = TextAlign.Right
    )
)