package com.pourkazemi.mahdi.dua.ui.theme

import android.app.Activity
import android.os.Build
import android.provider.CalendarContract
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.nestedscroll.NestedScrollSource.Companion.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

val DarkColorScheme = darkColorScheme(
    primary = BluePrimary,                // رنگ اصلی در حالت دارک
    onPrimary = WhiteText,                // رنگ متن روی دکمه‌های اصلی
    primaryContainer = BlueLight,         // رنگ پس‌زمینه دکمه‌های اصلی
    onPrimaryContainer = WhiteText,       // رنگ متن روی دکمه‌های اصلی در حالت Hover

    secondary = BlueAccent,               // رنگ ثانویه (برای تاکید‌ها)
    onSecondary = WhiteText,              // رنگ متن روی تاکید‌ها
    secondaryContainer = BlueLight,       // رنگ پس‌زمینه تاکید‌ها
    onSecondaryContainer = WhiteText,     // رنگ متن تاکید‌ها در حالت Hover

    background = BlueLight,               // پس‌زمینه کلی اپلیکیشن در حالت دارک
    onBackground = WhiteText,             // رنگ متن اصلی روی پس‌زمینه دارک
    surface = BluePrimary,                // سطح‌های مختلف
    onSurface = WhiteText,                // متن روی سطوح
    surfaceVariant = DividerColor,        // سطوح مختلف (کارت‌ها و ...)
    onSurfaceVariant = PrimaryText        // متن روی سطح‌های واریانت
)

val LightColorScheme = lightColorScheme(
    primary = BluePrimary,                // رنگ اصلی در حالت لایت
    onPrimary = WhiteText,                // رنگ متن روی دکمه‌های اصلی
    primaryContainer = LightPrimary,      // رنگ پس‌زمینه دکمه‌های اصلی
    onPrimaryContainer = BluePrimary,     // رنگ متن روی دکمه‌های اصلی در حالت Hover

    secondary = BlueAccent,               // رنگ ثانویه (برای تاکید‌ها)
    onSecondary = WhiteText,              // رنگ متن روی تاکید‌ها
    secondaryContainer = LightPrimary,    // رنگ پس‌زمینه تاکید‌ها
    onSecondaryContainer = WhiteText,     // رنگ متن تاکید‌ها در حالت Hover

    background = LightPrimary,            // پس‌زمینه کلی اپلیکیشن در حالت لایت
    onBackground = PrimaryText,           // رنگ متن اصلی روی پس‌زمینه لایت
    surface = LightPrimary,               // سطح‌های مختلف
    onSurface = PrimaryText,              // متن روی سطوح
    surfaceVariant = DividerColor,        // سطوح مختلف (کارت‌ها و ...)
    onSurfaceVariant = SecondaryText      // متن روی سطح‌های واریانت
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
        typography = Typography,
        content = content
    )
}