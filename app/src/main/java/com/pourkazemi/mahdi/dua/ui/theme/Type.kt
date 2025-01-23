package com.pourkazemi.mahdi.dua.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.style.LineBreak
import com.pourkazemi.mahdi.dua.R

/*// اضافه کردن ارائه‌دهنده فونت
val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)
// تعریف فونت گوگل
val fontName = GoogleFont("Amiri Quran")
// ساخت فونت‌فمیلی با فونت گوگل
val AmiriFontFamily = FontFamily(
    Font(
        googleFont = fontName,
        fontProvider = provider,
        weight = FontWeight.Medium,
        style = FontStyle.Normal
    )
)*/

val AmiriFontFamily = FontFamily(
    Font(R.font.amiriregular, FontWeight.Medium),
    Font(R.font.amiribold, FontWeight.Bold),
)


// تعریف LineBreak برای پاراگراف
val customTitleLineBreak = LineBreak(
    strategy = LineBreak.Strategy.Balanced,
    strictness = LineBreak.Strictness.Strict,
    wordBreak = LineBreak.WordBreak.Default
)

// Typography به‌روزرسانی‌شده
val MyTypography = Typography(

    bodyLarge = TextStyle(
        fontFamily = AmiriFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp, // تنظیم سایز فونت
        textDirection = TextDirection.Rtl, // جهت متن راست به چپ
        lineBreak = customTitleLineBreak // اعمال LineBreak
    ),

    bodyMedium = TextStyle(
        fontFamily = AmiriFontFamily,
        fontWeight = FontWeight.Bold,
        //lineHeight = 40.sp,
        fontSize = 18.sp, // تنظیم سایز فونت
        //textAlign = TextAlign.Right, // تراز به سمت راست
        textDirection = TextDirection.Rtl, // جهت متن راست به چپ
        lineBreak = customTitleLineBreak // اعمال LineBreak
    ),

    bodySmall = TextStyle(
        fontFamily = AmiriFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp, //12->1.15//
        //letterSpacing = 0.001.sp,
        //textAlign = TextAlign.Right, // تراز به سمت راست
        textDirection = TextDirection.Rtl, // جهت متن راست به چپ
        lineBreak = customTitleLineBreak // اعمال LineBreak
    )
)

