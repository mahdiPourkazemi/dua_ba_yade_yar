package com.pourkazemi.mahdi.dua.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import com.pourkazemi.mahdi.dua.ui.preview.FullPreview


class TranslationState(
    initialGlobalState: Boolean = false,
    initialIndividualStates: Map<Int, Boolean> = emptyMap()
) {
    var globalTranslationEnabled by mutableStateOf(initialGlobalState)
        private set

    var individualStates by mutableStateOf(initialIndividualStates)
        private set

    fun toggleGlobalTranslation(enabled: Boolean) {
        globalTranslationEnabled = enabled
        // Reset individual states when global state changes
        individualStates = mapOf()
    }

    fun toggleIndividualTranslation(itemId: Int) {
        val isCurrentlyVisible = isTranslationVisible(itemId)
        individualStates = individualStates + (itemId to !isCurrentlyVisible)    }

    fun isTranslationVisible(itemId: Int): Boolean {
        return individualStates[itemId] ?: globalTranslationEnabled
    }

    companion object {
        val Saver: Saver<TranslationState, *> = listSaver(
            save = { listOf(it.globalTranslationEnabled, it.individualStates) },
            restore = {
                TranslationState(
                    initialGlobalState = it[0] as Boolean,
                    initialIndividualStates = it[1] as Map<Int, Boolean>
                )
            }
        )
    }
}

@Composable
fun rememberTranslationState(): TranslationState {
    return rememberSaveable(saver = TranslationState.Saver) {
        TranslationState()
    }
}

@Composable
fun DuaItem(
    modifier: Modifier = Modifier,
    itemId: Int,
    translationState: TranslationState,
    text: String = "default",
    traText: String = "translate",
    textStyle: TextStyle,
    traTextStyle: TextStyle
) {
    val showTranslation = translationState.isTranslationVisible(itemId)

    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = text,
                    style = textStyle.copy(
                        color = MaterialTheme.colorScheme.onSurface,
                        textDirection = TextDirection.Rtl
                    ),
                    modifier = modifier
                )

                AnimatedVisibility(
                    visible = showTranslation,
                    enter = fadeIn() + expandVertically(),
                    exit = fadeOut() + shrinkVertically()
                ) {
                    Text(
                        text = traText,
                        style = traTextStyle.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textDirection = TextDirection.Rtl
                        ),
                        modifier = modifier.fillMaxWidth()
                    )
                }
            }

            FilledTonalIconButton(
                onClick = { translationState.toggleIndividualTranslation(itemId) },
                modifier = Modifier.align(Alignment.BottomStart),
                shape = CircleShape,
                colors = IconButtonDefaults.filledTonalIconButtonColors(
                    containerColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f),
                    contentColor = MaterialTheme.colorScheme.onSecondary
                )
            ) {
                Icon(
                    tint = MaterialTheme.colorScheme.secondary,
                    imageVector = if (showTranslation)
                        Icons.Rounded.KeyboardArrowUp
                    else
                        Icons.Rounded.KeyboardArrowDown,
                    contentDescription = if (showTranslation)
                        "Hide translation"
                    else
                        "Show translation",
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}


/*
@FullPreview()
@Composable
fun MyTextPreview() {
        DuaItem(
            text = "مهدی پورکاظمی تست این است باید بیشتر بررسی شود".repeat(3),
            traText = "معنی شود یا می شود پس بشود",
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                textDirection = TextDirection.Rtl,
                color = MaterialTheme.colorScheme.onSurface
            ),
            traTextStyle = MaterialTheme.typography.bodyLarge.copy(
                textDirection = TextDirection.Rtl,
                color = MaterialTheme.colorScheme.onSurface
            ),
            modifier = Modifier,
            //viewTranslation = false
        )
}*/
