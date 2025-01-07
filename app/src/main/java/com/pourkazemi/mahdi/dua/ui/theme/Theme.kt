package com.pourkazemi.mahdi.dua.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

val DarkColorScheme = darkColorScheme(
    background = BackgroundDark, // پس‌زمینه تیره
    onBackground = Ivory80,      // متن روی پس‌زمینه

    surface = Lapis40,           // رنگ سطوح
    onSurface = WhiteText,       // متن روی سطوح

    primary = Turquoise80,       // رنگ اصلی (برای دکمه‌ها)
    onPrimary = WhiteText,       // متن روی رنگ اصلی

    secondary = Gold40,          // رنگ ثانویه
    onSecondary = WhiteText,     // متن روی رنگ ثانویه
    onSecondaryContainer = Gold40.copy(alpha = 0.7f), // پس‌زمینه برای دکمه‌ها یا کادرهای تاکید

    error = Color(0xFFFF5252),   // رنگ خطا
    onError = WhiteText,         // متن روی خطا

    outline = Ivory40,           // خطوط یا مرزبندی
    surfaceVariant = Lapis80,    // حالت‌های مختلف سطوح
    onSurfaceVariant = Ivory80   // متن روی حالت‌های مختلف سطوح
)

val LightColorScheme = lightColorScheme(
    background = BackgroundLight, // پس‌زمینه روشن
    onBackground = TextPrimaryLight, // متن روی پس‌زمینه

    surface = Ivory80,            // رنگ سطوح
    onSurface = TextPrimaryLight, // متن روی سطوح

    primary = AccentTurquoise,    // رنگ اصلی
    onPrimary = PrimaryText,      // متن روی رنگ اصلی

    secondary = Turquoise40,      // رنگ ثانویه
    onSecondary = WhiteText,      // متن روی رنگ ثانویه
    onSecondaryContainer = Turquoise40.copy(alpha = 0.7f), // پس‌زمینه تاکید

    error = Gold40,               // رنگ خطا
    onError = WhiteText,          // متن روی خطا

    outline = Lapis40,            // خطوط یا مرزبندی
    surfaceVariant = Ivory40,     // حالت‌های مختلف سطوح
    onSurfaceVariant = Lapis80    // متن روی حالت‌های مختلف سطوح
)





@Composable
fun DuaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
 //   val colorScheme= DarkColorScheme
//    val colorScheme= LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = MyTypography,
        content = content
    )
}