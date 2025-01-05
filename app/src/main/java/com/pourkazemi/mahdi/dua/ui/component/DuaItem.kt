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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Constraints
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

        ElevatedCard(
            modifier = Modifier
                //.padding(16.dp)
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            )
        ) {
            Box(
                modifier = Modifier
                    //.padding(8.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()//.padding(vertical = 8.dp)
                //.padding(16.dp)
                ) {
                    Text(
                        text = ColoredArabicText(text),
                        style = style,
                        //softWrap=true,
                        //maxLines = 3,
                        //minLines = 1,
                        //overflow = TextOverflow.Visible,
                        modifier = modifier//.padding(start=32.dp)//.fillMaxWidth()
                    )

                    // Translation Text
                    AnimatedVisibility(
                        visible = viewTranslation,
                        enter = fadeIn() + expandVertically(),
                        exit = fadeOut() + shrinkVertically()
                    ) {

                            Text(
                                text = traText,
                                style = style.copy(
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                ),
                                modifier = modifier
                                    .padding(
                                        top = 8.dp
                                    )
                                    .fillMaxWidth()
                            )

                    }
                }

                FilledTonalIconButton(
                    onClick = { viewTranslation = !viewTranslation },
                    modifier = Modifier
                        .align(Alignment.BottomStart),
                    shape = CircleShape,
                    colors = IconButtonDefaults.filledTonalIconButtonColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.7f),
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                )  {
                    Icon(
                        imageVector = if (viewTranslation)
                            Icons.Rounded.KeyboardArrowUp
                        else
                            Icons.Rounded.KeyboardArrowDown,
                        contentDescription = if (viewTranslation)
                            "Hide translation"
                        else
                            "Show translation",
                        modifier = Modifier.size(20.dp)
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
            text = "مهدی پورکاظمی تست این است باید بیشتر بررسی شود".repeat(3),
            traText = "معنی شود یا می شود پس بشود",
            style = MaterialTheme.typography.bodyLarge.copy(
                textDirection = TextDirection.Rtl,
                color = MaterialTheme.colorScheme.onSurface
            ),
            modifier = Modifier,
        )
}