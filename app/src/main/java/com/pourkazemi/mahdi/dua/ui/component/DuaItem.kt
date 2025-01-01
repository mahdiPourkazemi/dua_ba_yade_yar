package com.pourkazemi.mahdi.dua.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.pourkazemi.mahdi.dua.ui.preview.FullPreview

@Composable
fun DuaItem(
    modifier: Modifier = Modifier,
    text: String = "default",
    traText: String = "translate",
    style: TextStyle,
) {
    var viewTranslation by rememberSaveable { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth(),
            //.padding(horizontal = 8.dp, vertical = 2.dp)
            //.clickable(onClick = clickOnItem), // کلیک روی کل کارت
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp,
            pressedElevation = 4.dp
        ),
        //shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                //.safeContentPadding()
                //.padding(8.dp)
                //.padding( start = 8.dp, end = 8.dp, bottom = 0.dp, top = 8.dp )
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Text(
                text = ColoredArabicText(text),
                style = style,
                //maxLines = 2,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.Start)
                    .heightIn(21.dp)
                    .padding(horizontal = 2.dp), // پدینگ عمودی کمتر
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                // دکمه نمایش/مخفی کردن ترجمه
                TextButton(
                    onClick = { viewTranslation = !viewTranslation },
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp)
                ) {
                    Icon(
                        imageVector = if (viewTranslation)
                            Filled.KeyboardArrowUp
                        else
                            Filled.KeyboardArrowDown,
                        contentDescription = if (viewTranslation)
                            "نمایش کمتر"
                        else
                            "نمایش بیشتر",
                        //modifier = modifier.size(16.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        //modifier = modifier.alignByBaseline(),
                        text = if (viewTranslation) "مخفی کردن ترجمه" else "نمایش ترجمه",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }

            AnimatedVisibility(
                visible = viewTranslation,
                enter = expandVertically() + fadeIn(),
                exit = shrinkVertically() + fadeOut()
            ) {
                Text(
                    text = traText,
                    modifier = Modifier
                        .fillMaxWidth(),
                        //.padding( top = 4.dp, bottom = 16.dp, start = 16.dp, end = 16.dp ),
                    style = style,
                    color = MaterialTheme.colorScheme.onSurfaceVariant

                )
            }
        }
    }
}

fun ColoredArabicText(text: String):AnnotatedString = buildAnnotatedString {
        text.forEach { char ->
            // بررسی اگر کاراکتر اعراب است، آن را رنگی کن
            if (char in "ًٌٍَُِّْ") { // اعراب عربی
                withStyle(style = SpanStyle(color = Color.Red)) {
                    append(char)
                }
            } else {
                withStyle(style = SpanStyle(color = Color.Black)) {
                    append(char)
                }
            }
        }
    }

@FullPreview()
@Composable
fun MyTextPreview() {
        DuaItem(
            text = "مهدی پورکاظمی تست این است باید بیشتر بررسی شود",
            traText = "معنی شود یا می شود پس بشود",
            style = MaterialTheme.typography.bodyLarge.copy(
                textDirection = TextDirection.Rtl,
                color = MaterialTheme.colorScheme.onSurface
            ),
            modifier = Modifier,
        )
}