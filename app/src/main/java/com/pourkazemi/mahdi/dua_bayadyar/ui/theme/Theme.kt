package com.pourkazemi.mahdi.dua_bayadyar.ui.theme

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
    background = BackgroundDark, // پس‌زمینه تیره (لاجوردی بسیار تیره)
    onBackground = TextPrimaryDark, // متن روی پس‌زمینه (فیروزه‌ای روشن)

    surface = Lapis40, // رنگ سطوح (لاجوردی تیره)
    onSurface = Ivory80, // متن روی سطوح (سفید عاجی روشن)

    primary = Turquoise80, // رنگ اصلی (فیروزه‌ای روشن)
    onPrimary = WhiteText, // متن روی رنگ اصلی (سفید)

    secondary = Turquoise40, // رنگ ثانویه (فیروزه‌ای تیره)
    onSecondary = WhiteText, // متن روی رنگ ثانویه (سفید)
    onSecondaryContainer = Turquoise40.copy(alpha = 0.7f), // پس‌زمینه برای دکمه‌ها یا کادرهای تاکید (فیروزه‌ای تیره شفاف)

    error = Color(0xFFFF5252), // رنگ خطا (قرمز روشن)
    onError = WhiteText, // متن روی خطا (سفید)

    outline = Ivory40, // خطوط یا مرزبندی (خاکستری مایل به فیروزه‌ای)
    surfaceVariant = Lapis80, // حالت‌های مختلف سطوح (لاجوردی)
    onSurfaceVariant = TextPrimaryDark // متن روی حالت‌های مختلف سطوح (فیروزه‌ای روشن)
)

val LightColorScheme = lightColorScheme(
    background = BackgroundLight, // پس‌زمینه روشن (سفید عاجی با تم سبزآبی)
    onBackground = TextPrimaryLight, // متن روی پس‌زمینه (لاجوردی بسیار تیره)

    surface = Ivory80, // رنگ سطوح (سفید عاجی روشن)
    onSurface = TextPrimaryLight, // متن روی سطوح (لاجوردی بسیار تیره)

    primary = AccentTurquoise, // رنگ اصلی (سبزآبی روشن)
    onPrimary = WhiteText, // متن روی رنگ اصلی (سفید)

    secondary = Turquoise40, // رنگ ثانویه (فیروزه‌ای تیره)
    onSecondary = WhiteText, // متن روی رنگ ثانویه (سفید)
    onSecondaryContainer = Turquoise40.copy(alpha = 0.7f), // پس‌زمینه تاکید (فیروزه‌ای تیره شفاف)

    error = Color(0xFFD32F2F), // رنگ خطا (قرمز تیره‌تر برای تم روشن)
    onError = WhiteText, // متن روی خطا (سفید)

    outline = Lapis40, // خطوط یا مرزبندی (لاجوردی بسیار تیره)
    surfaceVariant = Ivory40, // حالت‌های مختلف سطوح (خاکستری مایل به فیروزه‌ای)
    onSurfaceVariant = TextPrimaryLight // متن روی حالت‌های مختلف سطوح (لاجوردی بسیار تیره)
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


    MaterialTheme(
        colorScheme = colorScheme,
        typography = MyTypography,
        content = content
    )
}