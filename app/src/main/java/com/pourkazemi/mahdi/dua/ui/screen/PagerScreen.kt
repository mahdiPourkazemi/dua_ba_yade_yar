package com.pourkazemi.mahdi.dua.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.pourkazemi.mahdi.dua.data.model.PrayerText
import com.pourkazemi.mahdi.dua.data.model.PrayerWithText
import com.pourkazemi.mahdi.dua.ui.component.TranslationState
import com.pourkazemi.mahdi.dua.ui.theme.MyTypography

@Composable
fun AutoAdvancePager(
    pageItems: List<PrayerText>,
    modifier: Modifier = Modifier,
    translationState: TranslationState,
    textStyle: TextStyle,
    translationTextStyle: TextStyle,
) {
    val density = LocalDensity.current
    val paddingOfElement = 64.dp

    BoxWithConstraints(modifier = Modifier
        .background(MaterialTheme.colorScheme.background)
        .fillMaxSize()
    ) {
        val pagerState = rememberPagerState(pageCount = { pageItems.size })

        val maxWidth = with(density) {
            (maxWidth - paddingOfElement).toPx().toInt()
        }

        HorizontalPager(
            state = pagerState
        ) { page ->
            DuaScreen(
                prayerText = pageItems.get(page),
                maxWidth = maxWidth,
                textStyle = textStyle,
                translationTextStyle = translationTextStyle,
                translationState=translationState,
            )


        }

        PagerIndicator(pageItems.size, pagerState.currentPage)
    }
}

@Composable
fun PagerIndicator(
    pageCount: Int,
    currentPageIndex: Int,
    modifier: Modifier = Modifier
) {
    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            repeat(pageCount) { iteration ->
                val isSelected = currentPageIndex == iteration
                val color = if (isSelected) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                }

                Box(
                    modifier = modifier
                        .padding(horizontal = 4.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(24.dp), // اندازه بزرگ‌تر برای نشان دادن عدد
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${iteration + 1}", // عدد صفحه
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = if (isSelected)
                                MaterialTheme.colorScheme.onPrimary
                            else
                                MaterialTheme.colorScheme.onSurfaceVariant,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
    }
}